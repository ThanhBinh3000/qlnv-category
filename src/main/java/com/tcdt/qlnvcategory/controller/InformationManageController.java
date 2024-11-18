package com.tcdt.qlnvcategory.controller;

import com.tcdt.qlnvcategory.request.object.catalog.DigitalCertificateManagementReq;
import com.tcdt.qlnvcategory.request.object.catalog.InfoUtilitySoftwareManagementReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.service.DigitalCertificateManagementService;
import com.tcdt.qlnvcategory.service.InfoUtilitySoftwareManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/info-manage")
@RequiredArgsConstructor
@Api(tags = "Quản lý thông tin")
public class InformationManageController {

    private final DigitalCertificateManagementService digitalCertificateManagementService;
    private final InfoUtilitySoftwareManagementService infoUtilitySoftwareManagementService;

    @ApiOperation(value = "Thêm mới chứng thư số", response = List.class)
    @PostMapping(value = "/create-certificate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> createDigitalCertificate(@Valid @RequestBody DigitalCertificateManagementReq req) {
        return ResponseEntity.ok(digitalCertificateManagementService.createDigitalCertificate(req));
    }

    @ApiOperation(value = "Sửa chứng thư số", response = List.class)
    @PutMapping(value = "/update-certificate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> editDigitalCertificate(@Valid @RequestBody DigitalCertificateManagementReq req) {
        return ResponseEntity.ok(digitalCertificateManagementService.editDigitalCertificate(req));
    }

    @ApiOperation(value = "Xoá chứng thư số", response = List.class)
    @DeleteMapping(value = "/delete-certificate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> removeDigitalCertificate(@PathVariable("id") String id) {
        return ResponseEntity.ok(digitalCertificateManagementService.removeDigitalCertificate(id));
    }

    @ApiOperation(value = "Xoá nhiều chứng thư số", response = List.class)
    @PostMapping(value = "/delete-certificates", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> removeDigitalCertificateList(@RequestBody List<DigitalCertificateManagementReq> req) {
        return ResponseEntity.ok(digitalCertificateManagementService.removeDigitalCertificateList(req));
    }

    @ApiOperation(value = "Chi tiết chứng thư số", response = List.class)
    @GetMapping(value = "/detail-certificate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> digitalCertificateDetail(@PathVariable("id") String id) {
        return ResponseEntity.ok(digitalCertificateManagementService.digitalCertificateDetail(id));
    }

    @ApiOperation(value = "Export digital certificate to excel", response = List.class)
    @PostMapping(value = "/export-certificate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void digitalCertificateExport(@RequestBody DigitalCertificateManagementReq req, HttpServletResponse response) {
        digitalCertificateManagementService.digitalCertificateExport(req, response);
    }

    @ApiOperation(value = "Export info utility software to excel", response = List.class)
    @PostMapping(value = "/export-info-utility-software", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void infoUtilitySoftwareExport(@RequestBody InfoUtilitySoftwareManagementReq req, HttpServletResponse response) {
        infoUtilitySoftwareManagementService.infoUtilitySoftwareExport(req, response);
    }

    @ApiOperation(value = "Tìm kiếm chứng thư số", response = List.class)
    @PostMapping(value = "/search-certificate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> digitalCertificateSearch(@RequestBody DigitalCertificateManagementReq req) {
        return ResponseEntity.ok(digitalCertificateManagementService.digitalCertificateSearch(req));
    }

    @ApiOperation(value = "Thêm mới thông tin và phần mềm tiện ích", response = List.class)
    @PostMapping(value = "/create-info-utility-software", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> createInfoUtilitySoftware(@Valid @RequestBody InfoUtilitySoftwareManagementReq req,
                                                          HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(infoUtilitySoftwareManagementService.createInfoUtilitySoftware(req, httpServletRequest));
    }

    @ApiOperation(value = "Sửa thông tin và phần mềm tiện ích", response = List.class)
    @PutMapping(value = "/update-info-utility-software", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> editInfoUtilitySoftware(@Valid @RequestBody InfoUtilitySoftwareManagementReq req) {
        return ResponseEntity.ok(infoUtilitySoftwareManagementService.editInfoUtilitySoftware(req));
    }

    @ApiOperation(value = "Xoá thông tin và phần mềm tiện ích", response = List.class)
    @DeleteMapping(value = "/delete-info-utility-software/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> removeInfoUtilitySoftware(@PathVariable("id") String id) {
        return ResponseEntity.ok(infoUtilitySoftwareManagementService.removeInfoUtilitySoftware(id));
    }

    @ApiOperation(value = "Xoá thông tin và phần mềm tiện ích", response = List.class)
    @PostMapping(value = "/delete-info-utility-software", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> removeInfoUtilitySoftwareList(@RequestBody List<InfoUtilitySoftwareManagementReq> req) {
        return ResponseEntity.ok(infoUtilitySoftwareManagementService.removeInfoUtilitySoftwareList(req));
    }
    @ApiOperation(value = "Chi tiết thông tin và phần mềm tiện ích", response = List.class)
    @GetMapping(value = "/detail-info-utility-software/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> infoUtilitySoftwareDetail(@PathVariable("id") String id) {
        return ResponseEntity.ok(infoUtilitySoftwareManagementService.infoUtilitySoftwareDetail(id));
    }

    @ApiOperation(value = "Tìm kiếm thông tin và phần mềm tiện ích", response = List.class)
    @PostMapping(value = "/search-info-utility-software", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> infoUtilitySoftwareSearch(@RequestBody InfoUtilitySoftwareManagementReq req) {
        return ResponseEntity.ok(infoUtilitySoftwareManagementService.infoUtilitySoftwareSearch(req));
    }
}
