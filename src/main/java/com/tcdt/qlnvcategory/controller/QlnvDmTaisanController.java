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

import com.tcdt.qlnvcategory.repository.catalog.QlnvDmTaisanRepository;
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmTaisanReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmTaisanSearchReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.service.DonViService;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmDonvi;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmTaisan;
import com.tcdt.qlnvcategory.util.Contains;
import com.tcdt.qlnvcategory.util.PaginationSet;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/dmuc-taisan")
@Slf4j
@Api(tags = "Danh mục tài sản")
public class QlnvDmTaisanController extends BaseController {

	@Autowired
	private QlnvDmTaisanRepository qlnvDmTaisanRepository;
	
	@Autowired 
	DonViService donViService;
	

	@ApiOperation(value = "Lấy chi tiết thông tin tài sản", response = List.class)
	@GetMapping(value = "/chi-tiet/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> detail(
			@ApiParam(value = "ID tài sản", example = "1", required = true) @PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			Optional<QlnvDmTaisan> qOptional = qlnvDmTaisanRepository.findById(Long.parseLong(ids));
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

	@ApiOperation(value = "Lấy danh sách tài sản", response = List.class)
	@PostMapping(value = "/danh-sach", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> colection(@Valid @RequestBody QlnvDmTaisanSearchReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			int page = PaginationSet.getPage(objReq.getPaggingReq().getPage());
			int limit = PaginationSet.getLimit(objReq.getPaggingReq().getLimit());
			Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());

			//lay don vi thuoc user tu cache			
			//QlnvDmDonvi abc = donViService.getDonViById(Long.parseLong(getDvql(req)));
			//TODO: truyn tham so don vi de lay ra sanh sach tai san dvi minh quan ly
			Page<QlnvDmTaisan> data = qlnvDmTaisanRepository.selectParams(objReq.getMaTaisan(), objReq.getTenTaisan(),
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

	@ApiOperation(value = "Thêm mới tài sản", response = List.class)
	@PostMapping(value = "/them-moi", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> create(@Valid @RequestBody QlnvDmTaisanReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			if (qlnvDmTaisanRepository.findByMaTaisan(objReq.getMaTaisan()) != null)
				throw new UnsupportedOperationException("Mã tài sản đã tồn tại");

			QlnvDmTaisan dataMap = new ModelMapper().map(objReq, QlnvDmTaisan.class);

			dataMap.setTrangThai(Contains.HOAT_DONG);
			dataMap.setNguoiTao(getUserName(req));
			dataMap.setNgayTao(new Date());

			QlnvDmTaisan createCheck = qlnvDmTaisanRepository.save(dataMap);

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

	@ApiOperation(value = "Sửa chi tiết tài sản", response = List.class)
	@PostMapping(value = "/sua", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> edit(@Valid @RequestBody QlnvDmTaisanReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			Optional<QlnvDmTaisan> qOptional = qlnvDmTaisanRepository.findById(objReq.getId());

			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Mã tài sản không tồn tại");

			QlnvDmTaisan dataDTB = qOptional.get();
			QlnvDmTaisan dataMap = new ModelMapper().map(objReq, QlnvDmTaisan.class);

			updateObjectToObject(dataDTB, dataMap);

			dataDTB.setNguoiSua(getUserName(req));
			dataDTB.setNgaySua(new Date());

			QlnvDmTaisan createCheck = qlnvDmTaisanRepository.save(dataDTB);

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

	@ApiOperation(value = "Xóa tài sản", response = List.class)
	@GetMapping(value = "/xoa/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> delete(
			@ApiParam(value = "ID tài sản", example = "1", required = true) @PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			Optional<QlnvDmTaisan> qOptional = qlnvDmTaisanRepository.findById(Long.parseLong(ids));
			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			qlnvDmTaisanRepository.delete(qOptional.get());

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