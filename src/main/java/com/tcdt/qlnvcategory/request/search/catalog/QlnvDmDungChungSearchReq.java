package com.tcdt.qlnvcategory.request.search.catalog;


import com.tcdt.qlnvcategory.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmDungChungSearchReq extends BaseRequest {
    String ma;
    String maCha;
    String giaTri;
    String ghiChu;
    String loai;
}
