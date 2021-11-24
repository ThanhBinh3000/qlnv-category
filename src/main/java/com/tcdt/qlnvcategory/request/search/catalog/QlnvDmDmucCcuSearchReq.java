package com.tcdt.qlnvcategory.request.search.catalog;



import com.tcdt.qlnvcategory.request.BaseRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmDmucCcuSearchReq extends BaseRequest{
	@ApiModelProperty(example = "VT_1")
	String capDvi;

	@ApiModelProperty(example = "Nhóm công cụ")
	String nhomCcu;

	String loaiNhapxuat;

	String tenDinhmuc;

}
