package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmMsoVtuSearchReq extends BaseRequest {
	@ApiModelProperty(example = "MSVT_1")
	String maMsoVtu;
	@ApiModelProperty(example = "Tên mã số vật tư 1")
	String tenMsoVtu;

}
