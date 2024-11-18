package com.tcdt.qlnvcategory.controller;

import com.tcdt.qlnvcategory.request.DeleteRecordReq;
import com.tcdt.qlnvcategory.request.object.catalog.DmCTieuCLuongReq;
import com.tcdt.qlnvcategory.request.search.catalog.DmCTieuCLuongSearchReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.service.DmCTieuCLuongService;
import com.tcdt.qlnvcategory.util.Contains;
import com.tcdt.qlnvcategory.util.PathContains;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping(value = "/dmuc-ctieu-cluong")
@Slf4j
@Api(tags = "Danh mục định chỉ tiêu chất lượng")
public class DmCTieuCLuongController extends BaseController {
    @Autowired
    private DmCTieuCLuongService service;

    @ApiOperation(value = "Lấy danh sách", response = List.class)
    @PostMapping(value = PathContains.URL_TRA_CUU, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> colection(@Valid @RequestBody DmCTieuCLuongSearchReq objReq) {
        Resp resp = new Resp();
        try {
            resp.setData(service.searchPage(objReq));
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Thêm mới tài sản", response = List.class)
    @PostMapping(value = PathContains.URL_TAO_MOI, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> create(@Valid @RequestBody DmCTieuCLuongReq objReq) {
        Resp resp = new Resp();
        try {
            resp.setData(service.save(objReq));
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Sửa chi tiết tài sản", response = List.class)
    @PostMapping(value = PathContains.URL_CAP_NHAT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> edit(@Valid @RequestBody DmCTieuCLuongReq objReq) {
        Resp resp = new Resp();
        try {
            resp.setData(service.update(objReq));
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }


    @ApiOperation(value = "Chi tiết danh mục tài sản", response = List.class)
    @GetMapping(value = PathContains.URL_CHI_TIET + "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> detail(@PathVariable("ids") String ids) {
        Resp resp = new Resp();
        try {
            resp.setData(service.detail(ids));
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }


    @ApiOperation(value = "Xóa tài sản", response = List.class)
    @PostMapping(value = "/xoa", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> delete(@RequestBody DeleteRecordReq req) {
        Resp resp = new Resp();
        try {
            service.delete(req.getIds());
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }
}
