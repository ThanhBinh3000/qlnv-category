package com.tcdt.qlnvcategory.controller;

import com.tcdt.qlnvcategory.request.object.catalog.DmPhanLoaiDcReq;
import com.tcdt.qlnvcategory.request.search.catalog.DmPhanLoaiDcSearchReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.service.DmPhanLoaiDcService;
import com.tcdt.qlnvcategory.table.catalog.DmPhanLoaiDc;
import com.tcdt.qlnvcategory.util.Contains;
import com.tcdt.qlnvcategory.util.PathContains;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/dmuc-dchung")
@Slf4j
@Api(tags = "Danh mục phân loại dùng chung")
public class DmPhanLoaiDcController  extends  BaseController{
    @Autowired
    private DmPhanLoaiDcService dmPhanLoaiDcService;

    @ApiOperation(value = "Danh sách phân loại dùng chung",response = List.class)
    @PostMapping(value = PathContains.URL_TAT_CA,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> findAll(@Valid @RequestBody DmPhanLoaiDcReq objReq){
        Resp resp=new Resp();
        try {
            resp.setData(dmPhanLoaiDcService.findAll(objReq));
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        }catch (Exception e){
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Danh sách phân loại dùng chung theo trạng thái",response = List.class)
    @PostMapping(value = "trang-thai",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> findByTrangThai(@Valid @RequestBody DmPhanLoaiDcReq objReq){
        Resp resp=new Resp();
        try {
            resp.setData(dmPhanLoaiDcService.selectTrangThai());
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        }catch (Exception e){
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Chi tiết phân loại dùng chung theo trạng thái",response = List.class)
    @GetMapping(value =PathContains.URL_CHI_TIET+ "/{ids}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> detail(@PathVariable("ids") String ids){
        Resp resp=new Resp();
        try {
            DmPhanLoaiDc dmPhanLoaiDc = dmPhanLoaiDcService.getById(ids);
            resp.setData(dmPhanLoaiDc);
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        }catch (Exception e) {
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Thêm phân loại danh mục dùng chung",response = List.class)
    @PostMapping(value =PathContains.URL_TAO_MOI ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Resp> create(@Valid @RequestBody DmPhanLoaiDcReq objReq, HttpServletRequest req){
        Resp resp=new Resp();
        try {
            DmPhanLoaiDc createCheck=dmPhanLoaiDcService.save(objReq,req);
            resp.setData(createCheck);
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        }catch (Exception e){
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Sửa phân loại danh mục dùng chung",response = List.class)
    @PostMapping(value = PathContains.URL_CAP_NHAT,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp>edit(@Valid @RequestBody DmPhanLoaiDcReq objReq,HttpServletRequest req){
        Resp resp=new Resp();
        try{
            DmPhanLoaiDc createCheck= dmPhanLoaiDcService.update(objReq,req);
            resp.setData(createCheck);
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        }catch (Exception e){
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Đổi trạng thái phân loại danh mục dùng chung",response = List.class)
    @PostMapping(value = "/doi-trang-thai/{ids}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> changeStatus(
           @ApiParam(value = "ID phân loại dùng chung") @PathVariable("ids") long ids){
        Resp resp=new Resp();
        try {
            dmPhanLoaiDcService.softDelete(ids);
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        }catch (Exception e) {
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Xóa phân loại danh mục dùng chung",response = List.class)
    @PostMapping(value =PathContains.URL_XOA +"{ids}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> delete(
            @ApiParam(value = "ID phân loại dùng chung") @PathVariable("ids") long ids){
        Resp resp=new Resp();
        try {
            dmPhanLoaiDcService.delete(ids);
            resp.setStatusCode(Contains.RESP_SUCC);
            resp.setMsg("Thành công");
        }catch (Exception e) {
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Lấy danh sách phân trang search phân loại danh mục dùng chung", response = List.class)
    @PostMapping(value = PathContains.URL_TRA_CUU, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> colection(@Valid @RequestBody DmPhanLoaiDcSearchReq objReq) {
        Resp resp = new Resp();
        try {
           Page<DmPhanLoaiDc> data=dmPhanLoaiDcService.searchPage(objReq);
           resp.setData(data);
           resp.setStatusCode(Contains.RESP_SUCC);
           resp.setMsg("Thành công");
        }catch (Exception e) {
            resp.setStatusCode(Contains.RESP_FAIL);
            resp.setMsg(e.getMessage());
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok(resp);

    }

}
