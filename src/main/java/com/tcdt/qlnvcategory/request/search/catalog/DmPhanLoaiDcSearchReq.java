package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DmPhanLoaiDcSearchReq extends BaseRequest {
    String maPloai;
    String tenPloai;
    String trangThai;
}
