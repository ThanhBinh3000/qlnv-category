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

import com.tcdt.qlnvcategory.repository.catalog.QlnvDmLoaiKhoRepository;
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmLoaiKhoReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmLoaiKhoSearchReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmLoaiKho;
import com.tcdt.qlnvcategory.util.Contains;
import com.tcdt.qlnvcategory.util.PaginationSet;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/dmuc-lh-kho")
@Slf4j
@Api(tags = "Danh mục loại kho")
public class QlnvDmLoaiKhoController extends BaseController {

	@Autowired
	private QlnvDmLoaiKhoRepository qlnvDmLoaiKhoRepository;

	@ApiOperation(value = "Lấy chi tiết thông tin loại hình kho", response = List.class)
	@GetMapping(value = "/chi-tiet/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> detail(
			@ApiParam(value = "ID loại hình kho", example = "1", required = true) @PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			Optional<QlnvDmLoaiKho> qOptional = qlnvDmLoaiKhoRepository.findById(Long.parseLong(ids));
			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			resp.setData(qOptional);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Lấy danh sách loại hình kho", response = List.class)
	@PostMapping(value = "/danh-sach", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> colection(@Valid @RequestBody QlnvDmLoaiKhoSearchReq objReq) {
		Resp resp = new Resp();
		try {
			int page = PaginationSet.getPage(objReq.getPaggingReq().getPage());
			int limit = PaginationSet.getLimit(objReq.getPaggingReq().getLimit());
			Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());

			Page<QlnvDmLoaiKho> data = qlnvDmLoaiKhoRepository.selectParams(objReq.getMaLhKho(), objReq.getTenLhKho(),
					objReq.getTrangThai(), pageable);
			resp.setData(data);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Thêm mới loại hình kho", response = List.class)
	@PostMapping(value = "/them-moi", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> create(@Valid @RequestBody QlnvDmLoaiKhoReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			if (qlnvDmLoaiKhoRepository.findByMaLhKho(objReq.getMaLhKho()) != null)
				throw new UnsupportedOperationException("Mã loại hình kho đã tồn tại");

			QlnvDmLoaiKho dataMap = new ModelMapper().map(objReq, QlnvDmLoaiKho.class);

			dataMap.setTrangThai(Contains.HOAT_DONG);
			dataMap.setNguoiTao(getUserName(req));
			dataMap.setNgayTao(new Date());

			QlnvDmLoaiKho createCheck = qlnvDmLoaiKhoRepository.save(dataMap);

			resp.setData(createCheck);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Sửa chi tiết loại hình kho", response = List.class)
	@PostMapping(value = "/sua", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> edit(@Valid @RequestBody QlnvDmLoaiKhoReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			Optional<QlnvDmLoaiKho> qOptional = qlnvDmLoaiKhoRepository.findById(objReq.getId());

			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Mã loại hình kho không tồn tại");

			QlnvDmLoaiKho dataDTB = qOptional.get();
			QlnvDmLoaiKho dataMap = new ModelMapper().map(objReq, QlnvDmLoaiKho.class);

			updateObjectToObject(dataDTB, dataMap);

			dataDTB.setNguoiSua(getUserName(req));
			dataDTB.setNgaySua(new Date());

			QlnvDmLoaiKho createCheck = qlnvDmLoaiKhoRepository.save(dataDTB);

			resp.setData(createCheck);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Xóa loại hình kho", response = List.class)
	@GetMapping(value = "/xoa/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> delete(
			@ApiParam(value = "ID loại hình kho", example = "1", required = true) @PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			Optional<QlnvDmLoaiKho> qOptional = qlnvDmLoaiKhoRepository.findById(Long.parseLong(ids));
			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			qlnvDmLoaiKhoRepository.delete(qOptional.get());

			resp.setData(qOptional);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}
}