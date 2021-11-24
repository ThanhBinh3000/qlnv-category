package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmDMucPhiBQuanSearchReq extends BaseRequest {
	@ApiModelProperty(example = "Tên định mức")
	String tenDmuc;
	@ApiModelProperty(example = "Nhóm bảo quản")
	String nhomBquan;
}
