package com.tcdt.qlnvcategory.controller;

import com.tcdt.qlnvcategory.request.object.catalog.LoginConfigReq;
import com.tcdt.qlnvcategory.response.Resp;
import com.tcdt.qlnvcategory.service.LoginConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/login-config")
@RequiredArgsConstructor
@Api(tags = "Cấu hình đăng nhập")
public class LoginConfigController {

    private final LoginConfigService loginConfigService;

    @ApiOperation(value = "Sửa cấu hình đăng nhập", response = List.class)
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> updateLoginConfig(@RequestBody LoginConfigReq loginConfigReq) {
        return ResponseEntity.ok(loginConfigService.save(loginConfigReq));
    }
    @ApiOperation(value = "Chi tiết phê duyệt LCNT,QĐ PD KH MUA,BÁN TRỰC TIẾP ĐANG THỰC HIỆN", response = List.class)
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resp> loginConfigDetail() {
        return ResponseEntity.ok(loginConfigService.loginConfigDetail());
    }

}
