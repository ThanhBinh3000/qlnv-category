package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmKyBQuanSearchReq extends BaseRequest{
	@ApiModelProperty(example = "KL_1")
	String maKy;
	@ApiModelProperty(example = "Tên kỳ bản quản 1")
	String tenKy;

}
