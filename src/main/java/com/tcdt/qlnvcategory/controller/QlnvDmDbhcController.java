package com.tcdt.qlnvcategory.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.tcdt.qlnvcategory.repository.catalog.QlnvDmDbhcRepository;
import com.tcdt.qlnvcategory.request.DeleteRecordReq;
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmDbhcReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmDbhcSearchReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmDbhc;
import com.tcdt.qlnvcategory.util.Contains;
import com.tcdt.qlnvcategory.util.PaginationSet;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/dmuc-dbhc")
@Slf4j
@Api(tags = "Danh mục địa bàn hành chính")
public class QlnvDmDbhcController extends BaseController {

	@Autowired
	private QlnvDmDbhcRepository qlnvDmDbhcRepository;

	@ApiOperation(value = "Lấy chi tiết thông tin địa bàn hành chính", response = List.class)
	@GetMapping(value = "/chi-tiet/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> detail(@PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			Optional<QlnvDmDbhc> qOptional = qlnvDmDbhcRepository.findById(Long.parseLong(ids));
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

	@ApiOperation(value = "Lấy danh sách địa bàn hành chính", response = List.class)
	@PostMapping(value = "/danh-sach", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> colection(@Valid @RequestBody QlnvDmDbhcSearchReq objReq) {
		Resp resp = new Resp();
		try {
			int page = PaginationSet.getPage(objReq.getPaggingReq().getPage());
			int limit = PaginationSet.getLimit(objReq.getPaggingReq().getLimit());
			Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());

			Page<QlnvDmDbhc> data = qlnvDmDbhcRepository.selectParams(objReq.getMaDbhc(), objReq.getTenDbhc(),
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

	@ApiOperation(value = "Thêm mới địa bàn hành chính", response = List.class)
	@PostMapping(value = "/them-moi", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> create(@Valid @RequestBody QlnvDmDbhcReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			if (qlnvDmDbhcRepository.findByMaDbhc(objReq.getMaDbhc()) != null)
				throw new UnsupportedOperationException("Mã địa bàn hành chính đã tồn tại");

			QlnvDmDbhc dataMap = new ModelMapper().map(objReq, QlnvDmDbhc.class);

			QlnvDmDbhc parentDbhc = null;
			if (StringUtils.isNotEmpty(objReq.getMaCha()))
				parentDbhc = qlnvDmDbhcRepository.findByMaDbhc(objReq.getMaCha());

			dataMap.setParent(parentDbhc);
			dataMap.setTrangThai(Contains.HOAT_DONG);
			dataMap.setNguoiTao(getUserName(req));
			dataMap.setNgayTao(new Date());

			QlnvDmDbhc createCheck = qlnvDmDbhcRepository.save(dataMap);

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

	@ApiOperation(value = "Sửa chi tiết địa bàn hành chính", response = List.class)
	@PostMapping(value = "/sua", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> edit(@Valid @RequestBody QlnvDmDbhcReq objReq, HttpServletRequest req) {
		Resp resp = new Resp();
		try {
			Optional<QlnvDmDbhc> qOptional = qlnvDmDbhcRepository.findById(objReq.getId());

			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Mã địa bàn hành chính không tồn tại");

			QlnvDmDbhc dataDTB = qOptional.get();
			QlnvDmDbhc dataMap = new ModelMapper().map(objReq, QlnvDmDbhc.class);

			QlnvDmDbhc parentDbhc = null;
			if (StringUtils.isNotEmpty(objReq.getMaCha()))
				parentDbhc = qlnvDmDbhcRepository.findByMaDbhc(objReq.getMaCha());

			dataMap.setParent(parentDbhc);

			updateObjectToObject(dataDTB, dataMap);

			dataDTB.setNguoiSua(getUserName(req));
			dataDTB.setNgaySua(new Date());

			QlnvDmDbhc createCheck = qlnvDmDbhcRepository.save(dataDTB);

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

	@ApiOperation(value = "Xóa địa bàn hành chính", response = List.class)
	@GetMapping(value = "/xoa/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> delete(@PathVariable("ids") String ids) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(ids))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			Optional<QlnvDmDbhc> qOptional = qlnvDmDbhcRepository.findById(Long.parseLong(ids));
			if (!qOptional.isPresent())
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			qlnvDmDbhcRepository.delete(qOptional.get());

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

	@ApiOperation(value = "Xóa nhiều địa bàn hành chính", response = List.class)
	@PostMapping(value = "/xoa", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> delete(@Valid @RequestBody DeleteRecordReq objReq) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(objReq.getIds()))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");

			List<Long> items = Arrays.asList(objReq.getIds().split(",")).stream().map(s -> Long.parseLong(s.trim()))
					.collect(Collectors.toList());

			qlnvDmDbhcRepository.deleteWithIds(items);

			resp.setStatusCode(Contains.RESP_SUCC);
			resp.setMsg("Thành công");
		} catch (Exception e) {
			resp.setStatusCode(Contains.RESP_FAIL);
			resp.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	@ApiOperation(value = "Lấy danh sách địa bàn hành chính theo cấp địa bàn hành chính", response = List.class)
	@GetMapping(value = "/danh-sach/cap/{cap}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> level(
			@ApiParam(value = "1: Cấp tỉnh, 2: cấp Huyện, 3: cấp Xã", example = "1", required = true) @PathVariable("cap") String cap) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(cap))
				throw new UnsupportedOperationException("Không tìm thấy cấp địa bàn hành chính");
			Iterable<QlnvDmDbhc> qOptional = qlnvDmDbhcRepository.findByCapAndTrangThai(cap, Contains.HOAT_DONG);
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

	@ApiOperation(value = "Lấy danh sách địa bàn hành chính theo mã cha", response = List.class)
	@GetMapping(value = "/danh-sach/ma-cha/{maCha}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> recursive(
			@ApiParam(value = "Mã cha địa bàn hành chính", example = "77", required = true) @PathVariable("maCha") String maCha) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(maCha))
				throw new UnsupportedOperationException("Không tìm thấy mã cha");
			Iterable<QlnvDmDbhc> qOptional = qlnvDmDbhcRepository.findByMaChaCus(maCha, Contains.HOAT_DONG);
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