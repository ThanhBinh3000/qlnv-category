package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmTaisanSearchReq extends BaseRequest {
	@ApiModelProperty(example = "VT_1")
	String maTaisan;
	@ApiModelProperty(example = "Tên tài sản 1")
	String tenTaisan;
	String maDviTinh;
	String maDvi;

}
