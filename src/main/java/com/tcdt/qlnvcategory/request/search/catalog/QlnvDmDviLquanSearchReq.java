package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmDviLquanSearchReq extends BaseRequest{
	@ApiModelProperty(example = "MSVT_1")
	String maDvi;
	@ApiModelProperty(example = "Tên đơn vị liên quan 1")
	String tenDvi;
	@ApiModelProperty(example = "Mã hành chính đơn vị liên quan 1")
	String maHchinh;

	String typeDvi;
}
