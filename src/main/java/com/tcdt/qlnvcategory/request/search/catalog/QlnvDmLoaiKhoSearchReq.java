package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmLoaiKhoSearchReq extends BaseRequest{
	@ApiModelProperty(example = "KHO_2")
	String maLhKho;
	@ApiModelProperty(example = "Tên loại kho 2")
	String tenLhKho;

}
