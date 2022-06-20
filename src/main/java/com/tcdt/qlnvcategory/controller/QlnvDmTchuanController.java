package com.tcdt.qlnvcategory.controller;

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
import com.tcdt.qlnvcategory.repository.catalog.QlnvDmTchuanHdrRepository;
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmTchuanDtlReq;
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmTchuanHdrReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmTchuanHdrSearchReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.secification.QlnvDmTchuanHdrSpecification;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmTchuanDtl;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmTchuanHdr;
import com.tcdt.qlnvcategory.util.Contains;
import com.tcdt.qlnvcategory.util.ObjectMapperUtils;
import com.tcdt.qlnvcategory.util.PaginationSet;
import com.tcdt.qlnvcategory.util.PathContains;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = PathContains.DM_TIEU_CHUAN)
@Slf4j
@Api(tags = "Danh mục tiêu chuẩn kỹ thuật")
public class QlnvDmTchuanController extends BaseController {

	@Autowired
	private QlnvDmTchuanHdrRepository qlnvDmTchuanHdrRepository;

	@ApiOperation(value = "Thêm mới tiêu chuẩn kỹ thuật", response = List.class)
	@PostMapping(value = PathContains.URL_TAO_MOI, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> create(@Valid @RequestBody QlnvDmTchuanHdrReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			if (qlnvDmTchuanHdrRepository.findByMaHang(objReq.getMaHang()) != null)
				throw new UnsupportedOperationException("Mã tiêu chuẩn kỹ thuật đã tồn tại");

			QlnvDmTchuanHdr dataMap = new ModelMapper().map(objReq, QlnvDmTchuanHdr.class);

			dataMap.setTrangThai(Contains.HOAT_DONG);
			// Add thong tin detail
			List<QlnvDmTchuanDtlReq> dtlReqList = objReq.getDetails();
			List<QlnvDmTchuanDtl> dtls = ObjectMapperUtils.mapAll(dtlReqList, QlnvDmTchuanDtl.class);
			dataMap.setChildren(dtls);

			QlnvDmTchuanHdr createCheck = qlnvDmTchuanHdrRepository.save(dataMap);

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

	@ApiOperation(value = "Cập nhật tiêu chuẩn kỹ thuật", response = List.class)
	@PostMapping(value = PathContains.URL_CAP_NHAT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> edit(@Valid @RequestBody QlnvDmTchuanHdrReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			Optional<QlnvDmTchuanHdr> qOptional = qlnvDmTchuanHdrRepository.findById(objReq.getId());

			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Mã tiêu chuẩn kỹ thuật không tồn tại");

			objReq.setMaHang(null);
			QlnvDmTchuanHdr dataDTB = qOptional.get();
			QlnvDmTchuanHdr dataMap = new ModelMapper().map(objReq, QlnvDmTchuanHdr.class);

			updateObjectToObject(dataDTB, dataMap);

			// Add thong tin detail
			List<QlnvDmTchuanDtlReq> dtlReqList = objReq.getDetails();
			List<QlnvDmTchuanDtl> dtls = ObjectMapperUtils.mapAll(dtlReqList, QlnvDmTchuanDtl.class);
			dataDTB.setChildren(dtls);

			QlnvDmTchuanHdr createCheck = qlnvDmTchuanHdrRepository.save(dataDTB);

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

	@ApiOperation(value = "Lấy chi tiết thông tin tiêu chuẩn kỹ thuật", response = List.class)
	@GetMapping(value = PathContains.URL_CHI_TIET + "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> detail(
			@ApiParam(value = "ID tiêu chuẩn kỹ thuật", example = "1", required = true) @PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			Optional<QlnvDmTchuanHdr> qOptional = qlnvDmTchuanHdrRepository.findById(Long.parseLong(ids));
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

	@ApiOperation(value = "Lấy chi tiết thông tin tiêu chuẩn kỹ thuật", response = List.class)
	@GetMapping(value = PathContains.URL_CHI_TIET + "/ma-hh/{loaiVthh}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> detailByLoaiVthh(
			@ApiParam(value = "ID tiêu chuẩn kỹ thuật", example = "1", required = true) @PathVariable("loaiVthh") String loaiVthh) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(loaiVthh))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			QlnvDmTchuanHdr qOptional = qlnvDmTchuanHdrRepository.findByMaHang(loaiVthh);
			if (qOptional == null)
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

	@ApiOperation(value = "Tra cứu tiêu chuẩn kỹ thuật", response = List.class)
	@PostMapping(value = PathContains.URL_TRA_CUU, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> colection(@Valid @RequestBody QlnvDmTchuanHdrSearchReq objReq) {
		Resp resp = new Resp();
		try {
			int page = PaginationSet.getPage(objReq.getPaggingReq().getPage());
			int limit = PaginationSet.getLimit(objReq.getPaggingReq().getLimit());
			Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());

			Page<QlnvDmTchuanHdr> data = qlnvDmTchuanHdrRepository
					.findAll(QlnvDmTchuanHdrSpecification.buildSearchQuery(objReq), pageable);
			resp.setData(data);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(EnumResponse.RESP_FAIL.getValue());
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Xóa tiêu chuẩn kỹ thuật", response = List.class)
	@GetMapping(value = PathContains.URL_XOA + "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> delete(
			@ApiParam(value = "ID tiêu chuẩn kỹ thuật", example = "1", required = true) @PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			Optional<QlnvDmTchuanHdr> qOptional = qlnvDmTchuanHdrRepository.findById(Long.parseLong(ids));
			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			qlnvDmTchuanHdrRepository.delete(qOptional.get());

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

	@ApiOperation(value = "Lấy danh sách tiêu chuẩn kỹ thuật đang hoạt động", response = List.class)
	@GetMapping(value = PathContains.URL_DANH_SACH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> collect() {
		Resp resp = new Resp();
		try {
			QlnvDmTchuanHdrSearchReq objReq = new QlnvDmTchuanHdrSearchReq();
			objReq.setTrangThai(Contains.HOAT_DONG);

			Iterable<QlnvDmTchuanHdr> qOptional = qlnvDmTchuanHdrRepository
					.findAll(QlnvDmTchuanHdrSpecification.buildSearchQuery(objReq));

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