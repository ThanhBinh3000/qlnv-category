package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmHthucBQuanSearchReq extends BaseRequest {
	@ApiModelProperty(example = "MSLH_1")
	String maHthuc;
	@ApiModelProperty(example = "Tên hình thức")
	String tenHthuc;
}
