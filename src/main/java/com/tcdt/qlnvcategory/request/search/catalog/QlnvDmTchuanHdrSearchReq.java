package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmTchuanHdrSearchReq extends BaseRequest {
	@ApiModelProperty(example = "010201")
	String maHang;

	@ApiModelProperty(example = "IOS2002")
	String tenQchuan;

	@ApiModelProperty(example = "2019")
	String namQchuan;

	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
