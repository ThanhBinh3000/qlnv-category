package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmNhomDuanSearchReq extends BaseRequest{
	@ApiModelProperty(example = "DA_1")
	String maNhom;
	@ApiModelProperty(example = "Tên nhóm dự án 1")
	String tenNhom;
}
