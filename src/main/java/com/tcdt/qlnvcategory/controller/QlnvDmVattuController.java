package com.tcdt.qlnvcategory.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tcdt.qlnvcategory.enums.EnumResponse;
import com.tcdt.qlnvcategory.repository.catalog.QlnvDmVattuRepository;
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmVattuReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmVattuSearchReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.secification.QlnvDmVattuSpecification;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmVattu;
import com.tcdt.qlnvcategory.util.Contains;
import com.tcdt.qlnvcategory.util.PaginationSet;
import com.tcdt.qlnvcategory.util.PathContains;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = PathContains.DM_HANG)
@Slf4j
@Api(tags = "Danh mục danh mục hàng")
public class QlnvDmVattuController extends BaseController {

	@Autowired
	private QlnvDmVattuRepository qlnvDmVattuRepository;

	@ApiOperation(value = "Lấy chi tiết thông tin danh mục hàng", response = List.class)
	@GetMapping(value = PathContains.URL_CHI_TIET + "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> detail(
			@ApiParam(value = "ID danh mục hàng", example = "1", required = true) @PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			Optional<QlnvDmVattu> qOptional = qlnvDmVattuRepository.findById(Long.parseLong(ids));
			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			resp.setData(qOptional);
			resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
			resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
		} catch (Exception e) {
			resp.setStatusCode(EnumResponse.RESP_FAIL.getValue());
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Tra cứu danh mục hàng", response = List.class)
	@PostMapping(value = PathContains.URL_TRA_CUU, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> colection(@Valid @RequestBody QlnvDmVattuSearchReq objReq) {
		Resp resp = new Resp();
		try {
			int page = PaginationSet.getPage(objReq.getPaggingReq().getPage());
			int limit = PaginationSet.getLimit(objReq.getPaggingReq().getLimit());
			Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());

			Page<QlnvDmVattu> data = qlnvDmVattuRepository.findAll(QlnvDmVattuSpecification.buildSearchQuery(objReq),
					pageable);

			resp.setData(data);
			resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
			resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
		} catch (Exception e) {
			resp.setStatusCode(EnumResponse.RESP_FAIL.getValue());
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Lấy danh sách danh mục hàng", response = List.class)
	@PostMapping(value = PathContains.URL_DANH_SACH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> colectionAll() {
		Resp resp = new Resp();
		try {
			List<QlnvDmVattu> data = qlnvDmVattuRepository.findByTrangThai(Contains.HOAT_DONG);
			resp.setData(data);
			resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
			resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
		} catch (Exception e) {
			resp.setStatusCode(EnumResponse.RESP_FAIL.getValue());
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Thêm mới danh mục hàng", response = List.class)
	@PostMapping(value = PathContains.URL_TAO_MOI, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> create(@Valid @RequestBody QlnvDmVattuReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			if (qlnvDmVattuRepository.findByMa(objReq.getMa()) != null)
				throw new UnsupportedOperationException("Mã danh mục hàng đã tồn tại");

			QlnvDmVattu dataMap = new ModelMapper().map(objReq, QlnvDmVattu.class);

			QlnvDmVattu parentVattu = new QlnvDmVattu();
			if (StringUtils.isNotEmpty(objReq.getMaCha()))
				parentVattu = qlnvDmVattuRepository.findByMa(objReq.getMaCha());

			dataMap.setParent(parentVattu);
			dataMap.setTrangThai(
					objReq.getTrangThai().equals(Contains.HOAT_DONG) ? Contains.HOAT_DONG : Contains.NGUNG_HOAT_DONG);
			dataMap.setNguoiTao(getUserName(req));
			dataMap.setNgayTao(new Date());

			QlnvDmVattu createCheck = qlnvDmVattuRepository.save(dataMap);

			resp.setData(createCheck);
			resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
			resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
		} catch (Exception e) {
			resp.setStatusCode(EnumResponse.RESP_FAIL.getValue());
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Sửa chi tiết danh mục hàng", response = List.class)
	@PostMapping(value = PathContains.URL_CAP_NHAT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> edit(@Valid @RequestBody QlnvDmVattuReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			Optional<QlnvDmVattu> qOptional = qlnvDmVattuRepository.findById(objReq.getId());

			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Mã danh mục hàng không tồn tại");

			QlnvDmVattu dataDTB = qOptional.get();
			QlnvDmVattu dataMap = new ModelMapper().map(objReq, QlnvDmVattu.class);

			QlnvDmVattu parentVattu = new QlnvDmVattu();
			if (StringUtils.isNotEmpty(objReq.getMaCha()))
				parentVattu = qlnvDmVattuRepository.findByMa(objReq.getMaCha());

			dataMap.setParent(parentVattu);

			updateObjectToObject(dataDTB, dataMap);

			dataMap.setTrangThai(
					objReq.getTrangThai().equals(Contains.HOAT_DONG) ? Contains.HOAT_DONG : Contains.NGUNG_HOAT_DONG);
			dataDTB.setNguoiSua(getUserName(req));
			dataDTB.setNgaySua(new Date());

			QlnvDmVattu createCheck = qlnvDmVattuRepository.save(dataDTB);

			resp.setData(createCheck);
			resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
			resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
		} catch (Exception e) {
			resp.setStatusCode(EnumResponse.RESP_FAIL.getValue());
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Xóa danh mục hàng", response = List.class)
	@GetMapping(value = PathContains.URL_XOA + "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> delete(
			@ApiParam(value = "ID danh mục hàng", example = "1", required = true) @PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			Optional<QlnvDmVattu> qOptional = qlnvDmVattuRepository.findById(Long.parseLong(ids));
			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			qlnvDmVattuRepository.delete(qOptional.get());

			resp.setData(qOptional);
			resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
			resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
		} catch (Exception e) {
			resp.setStatusCode(EnumResponse.RESP_FAIL.getValue());
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}
}