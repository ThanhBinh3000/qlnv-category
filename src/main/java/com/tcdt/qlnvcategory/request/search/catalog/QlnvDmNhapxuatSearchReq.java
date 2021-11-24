package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmNhapxuatSearchReq extends BaseRequest {
	@ApiModelProperty(example = "LH_1")
	String maLhinh;
	@ApiModelProperty(example = "Tên loại hình 1")
	String tenLhinh;
}
