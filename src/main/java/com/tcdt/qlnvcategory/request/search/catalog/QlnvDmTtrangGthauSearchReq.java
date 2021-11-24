package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmTtrangGthauSearchReq extends BaseRequest{
	@ApiModelProperty(example = "PT_1")
	String maTtrang;
	@ApiModelProperty(example = "Tên tình trạng gói thầu 1")
	String tenTtrang;

}
