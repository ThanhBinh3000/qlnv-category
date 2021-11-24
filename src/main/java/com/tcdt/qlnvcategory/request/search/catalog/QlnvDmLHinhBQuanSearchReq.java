package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmLHinhBQuanSearchReq extends BaseRequest {
	@ApiModelProperty(example = "MSLH_1")
	String maLhinh;
	@ApiModelProperty(example = "Tên loại hình")
	String tenLhinh;
}
