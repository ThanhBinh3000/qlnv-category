package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmMsoLthucSearchReq extends BaseRequest {
	@ApiModelProperty(example = "MSVT_1")
	String maMsoLthuc;
	@ApiModelProperty(example = "Tên mã số lương thực 1")
	String tenMsoLthuc;

}
