package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import lombok.Data;

@Data
public class QlnvDmDinhMucHaoHutSearchReq extends BaseRequest {
    String maDvi;
    String tenDinhMuc;
    String loaiVthh;
    String cloaiVthh;
    String hinhThucBq;
    String loaiHinhBq;
    String phuongThucBq;
}
