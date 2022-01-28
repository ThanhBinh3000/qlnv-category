package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmVattuSearchReq extends BaseRequest{
	@ApiModelProperty(example = "VT_1")
	String ma;
	
	@ApiModelProperty(example = "Tên hàng 1")
	String ten;
	
	String maLoai;
	
	String qcpc;
	
}
