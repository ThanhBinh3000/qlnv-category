package com.tcdt.qlnvcategory.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tcdt.qlnvcategory.repository.catalog.DanhMucRepository;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.table.catalog.QlnvDanhMuc;
import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/dmuc-chung")
@Slf4j
@Api(tags = "Danh mục dùng chung")
public class QlnvDanhMucController extends BaseController {

	@Autowired
	private DanhMucRepository danhMucRepository;

	@ApiOperation(value = "Lấy danh sách danh mục theo loại", response = List.class)
	@GetMapping(value = "/danh-sach/{loai}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resp> collect(@PathVariable("loai") String loai) {
		Resp resp = new Resp();
		try {
			if (StringUtils.isEmpty(loai))
				throw new UnsupportedOperationException("Không tồn tại bản ghi");
			
			Iterable<QlnvDanhMuc> qOptional = danhMucRepository.findByLoaiAndTrangThaiOrderByThuTuHienThi(loai, Contains.HOAT_DONG);

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