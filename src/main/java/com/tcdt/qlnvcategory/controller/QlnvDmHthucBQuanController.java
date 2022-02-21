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

import com.tcdt.qlnvcategory.repository.catalog.QlnvDmHthucBQuanRepository;
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmHthucBQuanReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmHthucBQuanSearchReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmHthucBQuan;
import com.tcdt.qlnvcategory.util.Contains;
import com.tcdt.qlnvcategory.util.PaginationSet;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/dmuc-hthuc-bquan")
@Slf4j
@Api(tags = "Danh mục hình thức bảo quản")
public class QlnvDmHthucBQuanController extends BaseController {

	@Autowired
	private QlnvDmHthucBQuanRepository dmHthucBQuanRepository;

	@ApiOperation(value = "Lấy chi tiết thông tin hình thức bảo quản", response = List.class)
	@GetMapping(value = "/find/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> detail(
			@ApiParam(value = "ID hình thức bảo quản", example = "1", required = true) @PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			Optional<QlnvDmHthucBQuan> qOptional = dmHthucBQuanRepository.findById(Long.parseLong(ids));
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

	@ApiOperation(value = "Lấy danh sách hình thức bảo quản", response = List.class)
	@PostMapping(value = "/findList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> colection(@Valid @RequestBody QlnvDmHthucBQuanSearchReq objReq) {
		Resp resp = new Resp();
		try {
			int page = PaginationSet.getPage(objReq.getPaggingReq().getPage());
			int limit = PaginationSet.getLimit(objReq.getPaggingReq().getLimit());
			Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());

			Page<QlnvDmHthucBQuan> data = dmHthucBQuanRepository.selectParams(objReq.getMaHthuc(), objReq.getTenHthuc(),
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

	@ApiOperation(value = "Thêm mới hình thức bảo quản", response = List.class)
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> create(@Valid @RequestBody QlnvDmHthucBQuanReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {

			QlnvDmHthucBQuan dataMap = new ModelMapper().map(objReq, QlnvDmHthucBQuan.class);

			dataMap.setTrangThai(Contains.HOAT_DONG);
			dataMap.setNguoiTao(getUserName(req));
			dataMap.setNgayTao(new Date());

			QlnvDmHthucBQuan createCheck = dmHthucBQuanRepository.save(dataMap);

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

	@ApiOperation(value = "Sửa chi tiết hình thức bảo quản", response = List.class)
	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> edit(@Valid @RequestBody QlnvDmHthucBQuanReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			Optional<QlnvDmHthucBQuan> qOptional = dmHthucBQuanRepository.findById(objReq.getId());

			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("hình thức bảo quản không tồn tại");

			QlnvDmHthucBQuan dataDTB = qOptional.get();
			QlnvDmHthucBQuan dataMap = new ModelMapper().map(objReq, QlnvDmHthucBQuan.class);

			updateObjectToObject(dataDTB, dataMap);

			dataDTB.setNguoiSua(getUserName(req));
			dataDTB.setNgaySua(new Date());

			QlnvDmHthucBQuan createCheck = dmHthucBQuanRepository.save(dataDTB);

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

	@ApiOperation(value = "Xóa hình thức bảo quản", response = List.class)
	@GetMapping(value = "/delete/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> delete(
			@ApiParam(value = "ID hình thức", example = "1", required = true) @PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			Optional<QlnvDmHthucBQuan> qOptional = dmHthucBQuanRepository.findById(Long.parseLong(ids));
			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			dmHthucBQuanRepository.delete(qOptional.get());

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