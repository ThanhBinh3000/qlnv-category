package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmDviCuutroSearchReq extends BaseRequest {
	@ApiModelProperty(example = "MSVT_1")
	String maDviCuutro;
	@ApiModelProperty(example = "Tên đơn vị cứu trợ 1")
	String tenDviCuutro;
}
