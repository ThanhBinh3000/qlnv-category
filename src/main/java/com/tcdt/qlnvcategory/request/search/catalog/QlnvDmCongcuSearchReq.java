package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmCongcuSearchReq extends BaseRequest{
	@ApiModelProperty(example = "CC_1")
	String maCcu;
	@ApiModelProperty(example = "Tên công cụ bảo quản thầu 1")
	String tenCcu;
}
