package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmPthucBquanSearchReq extends BaseRequest{
	@ApiModelProperty(example = "PT_1")
	String maPthuc;
	@ApiModelProperty(example = "Tên phương thức bảo quản 1")
	String tenPthuc;

}
