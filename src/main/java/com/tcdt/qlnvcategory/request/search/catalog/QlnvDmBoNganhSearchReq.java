package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmBoNganhSearchReq extends BaseRequest {
	@ApiModelProperty(example = "CQTW")
	String ma;
	@ApiModelProperty(example = "Cơ quan của Trung ương Đảng")
	String ten;
}
