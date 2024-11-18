package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import lombok.Data;

@Data
public class DmCTieuCLuongSearchReq extends BaseRequest {
    String maDvi;
    String maChiTieu;
    String tenChiTieu;
    String loaiVthh;
    String trangThai;
}
