package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmQuocgiaSearchReq extends BaseRequest{
	@ApiModelProperty(example = "QG_1")
	String maQgia;
	@ApiModelProperty(example = "Tên quốc gia sản xuất 1")
	String tenQgia;

}
