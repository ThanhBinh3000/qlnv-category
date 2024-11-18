package com.tcdt.qlnvcategory.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcdt.qlnvcategory.request.DeleteReq;
import com.tcdt.qlnvcategory.service.DmDmucPhiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmDMucPhiBQuanReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmDMucPhiBQuanSearchReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmDMucPhi;
import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/dmuc-dmuc-phi")
@Slf4j
@Api(tags = "Danh mục định mức phí")
public class QlnvDmDmucPhiController extends BaseController {

    @Autowired
    private DmDmucPhiService dmDmucPhiService;

    @ApiOperation(value = "Lấy chi tiết thông tin định mức phí bảo quản", response = List.class)
    @GetMapping(value = "/chi-tiet/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> detail(
            @ApiParam(value = "ID định mức phí bảo quản", example = "1", required = true) @PathVariable("ids") String ids) {
        Resp resp = new Resp();
        try {
            if (StringUtils.isEmpty(ids))
                throw new UnsupportedOperationException("Không tồn tại bản ghi");
            Optional<QlnvDmDMucPhi> qOptional = dmDmucPhiService.findById(Long.parseLong(ids));
            if (!qOptional.isPresent())
                throw new UnsupportedOperationException("Không tồn tại bản ghi");
            resp.setData(qOptional);
            resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
            resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
        } catch (Exception e) {
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Lấy danh sách định mức phí bảo quản", response = List.class)
    @PostMapping(value = "/danh-sach", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> colection(@Valid @RequestBody QlnvDmDMucPhiBQuanSearchReq objReq) {
        Resp resp = new Resp();
        try {
            resp.setData(dmDmucPhiService.searchPage(objReq));
            resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
            resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
        } catch (Exception e) {
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Lấy danh sách định mức phí bảo quản", response = List.class)
    @PostMapping(value = "/danh-sach-chua-su-dung", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> searchCollection(@Valid @RequestBody QlnvDmDMucPhiBQuanSearchReq objReq) {
        Resp resp = new Resp();
        try {
            resp.setData(dmDmucPhiService.searchDSChuaSuDung(objReq));
            resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
            resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Lấy danh sách danh mục định mức phí bảo quản đã đc Tổng cục ra QĐ", response = List.class)
    @PostMapping(value = "/danh-sach-tong-cuc-ra-qd", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> searchCollectionTcApDung(@Valid @RequestBody QlnvDmDMucPhiBQuanSearchReq objReq) {
        Resp resp = new Resp();
        try {
            resp.setData(dmDmucPhiService.searchDSTongCucApDung(objReq));
            resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
            resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
        } catch (Exception e) {
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Thêm mới định mức phí bảo quản", response = List.class)
    @PostMapping(value = "/them-moi", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> create(@Valid @RequestBody QlnvDmDMucPhiBQuanReq objReq, HttpServletRequest req) {
        Resp resp = new Resp();
        try {
            resp.setData(dmDmucPhiService.create(objReq));
            resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
            resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Sửa chi tiết định mức phí bảo quản", response = List.class)
    @PostMapping(value = "/sua", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> edit(@Valid @RequestBody QlnvDmDMucPhiBQuanReq objReq, HttpServletRequest req) {
        Resp resp = new Resp();
        try {
            resp.setData(dmDmucPhiService.update(objReq));
            resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
            resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
        } catch (Exception e) {
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Xóa định mức phí", response = List.class)
    @GetMapping(value = "/xoa/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> delete(
            @ApiParam(value = "ID định mức", example = "1", required = true) @PathVariable("ids") Long ids) {
        Resp resp = new Resp();
        try {
            resp.setData(dmDmucPhiService.detele(ids));
            resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
            resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Xóa định mức phí", response = List.class)
    @PostMapping(value = "/xoa/multi", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> deleteMultiple(@RequestBody @Valid DeleteReq req) {
        Resp resp = new Resp();
        try {
            resp.setData(dmDmucPhiService.deleteMultiple(req.getIds()));
            resp.setStatusCode(EnumResponse.RESP_SUCC.getValue());
            resp.setMsg(EnumResponse.RESP_SUCC.getDescription());
        } catch (Exception e) {
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }


    @ApiOperation(value = "Kết xuất danh sách", response = List.class)
    @PostMapping(value = "/ket-xuat", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void exportListDxPagToExcel(@Valid @RequestBody QlnvDmDMucPhiBQuanSearchReq objReq, HttpServletResponse response) throws Exception {
        try {
            dmDmucPhiService.export(objReq, response);
        } catch (Exception e) {
            final Map<String, Object> body = new HashMap<>();
            body.put("statusCode", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            body.put("msg", e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), body);
        }
    }

}