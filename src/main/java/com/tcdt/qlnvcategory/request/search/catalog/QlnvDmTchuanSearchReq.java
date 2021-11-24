package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmTchuanSearchReq extends BaseRequest{
	@ApiModelProperty(example = "TC_1")
	String maTchuan;
	@ApiModelProperty(example = "Tên tiêu chuẩn 1")
	String tenTchuan;

}
