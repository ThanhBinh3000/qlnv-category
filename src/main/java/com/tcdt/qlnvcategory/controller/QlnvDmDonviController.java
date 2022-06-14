package com.tcdt.qlnvcategory.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.tcdt.qlnvcategory.table.UserInfo;
import com.tcdt.qlnvcategory.util.PathContains;
import com.tcdt.qlnvcategory.util.UserUtils;
import org.apache.commons.lang3.ObjectUtils;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.tcdt.qlnvcategory.entities.catalog.QlnvDmDonviEntity;
import com.tcdt.qlnvcategory.repository.catalog.QlnvDmDonviEntityRepository;
import com.tcdt.qlnvcategory.repository.catalog.QlnvDmDonviRepository;
import com.tcdt.qlnvcategory.request.BaseRequest;
import com.tcdt.qlnvcategory.request.DeleteRecordReq;
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmDonviReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmDonviSearchReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.service.DonViService;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmDonvi;
import com.tcdt.qlnvcategory.util.Contains;
import com.tcdt.qlnvcategory.util.PaginationSet;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/dmuc-donvi")
//TODO: sua thanh cotaint
@Slf4j
@Api(tags = "Danh mục đơn vị")
public class QlnvDmDonviController extends BaseController {

	@Autowired
	private QlnvDmDonviRepository qlnvDmDonviRepository;

	@Autowired
	private QlnvDmDonviEntityRepository qDmDonviEntityRepository;

	@Autowired
	private DonViService donViService;

	// TODO: dat quy tac chung path, quản lý ở 1 fileURL_THEM="/them";
	// URL_SUA="sua";
	@ApiOperation(value = "Lấy chi tiết thông tin đơn vị", response = List.class)
	@GetMapping(value = PathContains.URL_CHI_TIET + "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> detail(@PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			QlnvDmDonvi dmDonvi = donViService.getDonViById(ids);
			resp.setData(dmDonvi);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}
	//@PreAuthorize("hasRole('DM_DV')")
	@ApiOperation(value = "Lấy danh sách đơn vị", response = List.class)
	@PostMapping(value = "/danh-sach", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> colection(@Valid @RequestBody QlnvDmDonviSearchReq objReq) {
		Resp resp = new Resp();
		try {
			int page = PaginationSet.getPage(objReq.getPaggingReq().getPage());
			int limit = PaginationSet.getLimit(objReq.getPaggingReq().getLimit());
			Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());

			Page<QlnvDmDonviEntity> data = qDmDonviEntityRepository.selectParams(objReq.getMaDvi(), objReq.getTenDvi(),
					objReq.getTrangThai(), objReq.getMaTinh(), objReq.getMaQuan(), objReq.getMaPhuong(),
					objReq.getCapDvi(), objReq.getKieuDvi(), objReq.getLoaiDvi(), pageable);
			resp.setData(data);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Thêm mới đơn vị", response = List.class)
	@PostMapping(value = PathContains.URL_TAO_MOI, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> create(@Valid @RequestBody QlnvDmDonviReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			QlnvDmDonvi createCheck = donViService.save(objReq,req);
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

	@ApiOperation(value = "Sửa chi tiết đơn vị", response = List.class)
	@PostMapping(value = PathContains.URL_CAP_NHAT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> edit(@Valid @RequestBody QlnvDmDonviReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			QlnvDmDonvi createCheck = donViService.update(objReq,req);
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

	@ApiOperation(value = "Xóa đơn vị", response = List.class)
	@PostMapping(value = PathContains.URL_XOA+"/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> delete(@PathVariable("ids") long ids) {
		Resp resp = new Resp();
		try {
			donViService.delete(ids);
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Xóa nhiều đơn vị", response = List.class)
	@PostMapping(value = "/xoa", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> delete(@Valid @RequestBody DeleteRecordReq objReq) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(objReq.getIds()))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			List<Long> items = Arrays.asList(objReq.getIds().split(",")).stream().map(s -> Long.parseLong(s.trim()))
					.collect(Collectors.toList());

			qlnvDmDonviRepository.deleteWithIds(items);

			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Lấy danh sách đơn vị đang hoạt động", response = List.class)
	@GetMapping(value = "/danh-sach/hoat-dong", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> collect() {
		Resp resp = new Resp();
		try {
			Iterable<QlnvDmDonvi> qOptional = qlnvDmDonviRepository.findByTrangThai(Contains.HOAT_DONG);
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

	@ApiOperation(value = "Lấy danh sách đơn vị đang hoạt động", response = List.class)
	@GetMapping(value = PathContains.URL_DANH_SACH+"/dvi-cha", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> collectAll() {
		Resp resp = new Resp();
		try {
			Iterable<QlnvDmDonvi> qOptional = qlnvDmDonviRepository.findByMaDviChaIsNull();
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

	@ApiOperation(value = "Lấy chi tiết thông tin đơn vị theo mã", response = List.class)
	@PostMapping(value = "/chi-tiet", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> detailbycode(@Valid @RequestBody BaseRequest objReq) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(objReq.getStr()))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			Optional<QlnvDmDonvi> qOptional = qlnvDmDonviRepository.findByMaDvi(objReq.getStr());
			if (qOptional == null)
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

	@ApiOperation(value = "Lấy danh sách đơn vị con", response = List.class)
	@GetMapping(value = "/ds-donvi-child", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> colectionChild() {
		Resp resp = new Resp();
		try {
			UserInfo userInfo = UserUtils.getUserInfo();
			Iterable<QlnvDmDonviEntity> data = qDmDonviEntityRepository.findByMaDviChaAndTrangThai(userInfo.getDvql(),Contains.HOAT_DONG);
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

	@ApiOperation(value = "Lấy tất cả các đơn vị theo cấp đơn vị", response = List.class)
	@PostMapping(value = PathContains.URL_TAT_CA, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> getAll(@Valid @RequestBody QlnvDmDonviSearchReq objReq) {
		Resp resp = new Resp();
		try {
			resp.setData(donViService.getAll(objReq));
			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Lấy tất cả các đơn vị theo cấp đơn vị", response = List.class)
	@GetMapping(value = PathContains.URL_TAT_CA+ "/{capDvi}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> getAllByLevel(@PathVariable("capDvi") String capDvi) {
		Resp resp = new Resp();
		try {
			resp.setData(donViService.getAllByLevel(capDvi,"01"));
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