package com.tcdt.qlnvcategory.controller;

import java.util.List;

import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmDungChungReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmDungChungSearchReq;
import com.tcdt.qlnvcategory.service.DanhMucDungChungService;
import com.tcdt.qlnvcategory.util.PaginationSet;
import com.tcdt.qlnvcategory.util.PathContains;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tcdt.qlnvcategory.repository.catalog.DanhMucRepository;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.table.catalog.QlnvDanhMuc;
import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/dmuc-chung")
@Slf4j
@Api(tags = "Danh mục dùng chung")
public class QlnvDanhMucController extends BaseController {

	@Autowired
	private DanhMucDungChungService danhMucDungChungService;


	@ApiOperation(value = "Lấy danh sách danh mục theo loại", response = List.class)
	@GetMapping(value = PathContains.URL_DANH_SACH + "/{loai}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> collect(@PathVariable("loai") String loai) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(loai)) throw new UnsupportedOperationException("Không tồn tại bản ghi");

			Iterable<QlnvDanhMuc> qOptional = danhMucDungChungService.getByLoaiAndTrangThaiOrderByThuTuHienThi(loai,
					Contains.HOAT_DONG);

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

	@ApiOperation(value = "Tra cứu danh mục dùng chung", response = List.class)
	@PostMapping(value = PathContains.URL_TRA_CUU, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> collection(@Valid @RequestBody QlnvDmDungChungSearchReq objReq) {
		Resp resp = new Resp();
		try {
			Page<QlnvDanhMuc> data = danhMucDungChungService.getAll(objReq);
			resp.setData(data);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		}catch (Exception e) {
			resp.setData(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Lấy danh sách danh mục dùng chung", response = List.class)
	@GetMapping(value = PathContains.URL_TAT_CA, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> collections() {
		Resp resp = new Resp();
		try {
			Iterable<QlnvDanhMuc> data = danhMucDungChungService.getAll();
			resp.setData(data);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		}catch (Exception e) {
			resp.setData(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}


	@ApiOperation(value = "Lấy chi tiết thông tin danh mục",response = List.class)
	@GetMapping(value = PathContains.URL_CHI_TIET + "/{ids}" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> detail(@PathVariable long ids) {
		Resp resp = new Resp();
		try {
			QlnvDanhMuc data = danhMucDungChungService.getById(ids);
			resp.setData(data);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		}catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}


	@ApiOperation(value = "Thêm mới danh mục dùng chung", response = List.class)
	@PostMapping(value = PathContains.URL_TAO_MOI, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Resp> create(@Valid @RequestBody QlnvDmDungChungReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {

			QlnvDanhMuc createCheck = danhMucDungChungService.save(objReq, req);

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

	@ApiOperation(value = "Sửa chi tiết danh mục dùng chung", response = List.class)
	@PostMapping(value = PathContains.URL_CAP_NHAT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> edit(@Valid @RequestBody QlnvDmDungChungReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {

			QlnvDanhMuc createCheck = danhMucDungChungService.update(objReq, req);

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

	@ApiOperation(value = "Xóa danh mục dùng chung", response = List.class)
	@PostMapping(value = PathContains.URL_XOA + "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> delete(@PathVariable("ids") long ids) {
		Resp resp = new Resp();
		try {
			danhMucDungChungService.delete(ids);
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
