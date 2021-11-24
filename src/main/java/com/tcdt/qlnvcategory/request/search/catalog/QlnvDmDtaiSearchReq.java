package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmDtaiSearchReq extends BaseRequest {
	@ApiModelProperty(example = "MSDT_1")
	String maDmDt;
	@ApiModelProperty(example = "Tên đề tài")
	String tenDmDt;
}
