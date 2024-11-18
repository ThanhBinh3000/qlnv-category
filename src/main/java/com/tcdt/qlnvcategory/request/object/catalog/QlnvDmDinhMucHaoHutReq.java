package com.tcdt.qlnvcategory.request.object.catalog;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QlnvDmDinhMucHaoHutReq {
    private Long id;
    String maDvi;
    String maDinhMuc;
    String tenDinhMuc;
    String loaiVthh;
    String cloaiVthh;
    String hinhThucBq;
    String loaiHinhBq;
    String phuongThucBq;
    Long tgBaoQuanTu;
    Long tgBaoQuanDen;
    BigDecimal dinhMuc;
    String apDungTai;
}
