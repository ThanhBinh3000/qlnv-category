package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmTchuanHdrSearchReq extends BaseRequest {
	@ApiModelProperty(example = "MHH001")
	String maHang;

	@ApiModelProperty(example = "IOS2002")
	String tenQchuan;

	@ApiModelProperty(example = Contains.LOAI_NHAP)
	String loaiNx;

	@ApiModelProperty(example = Contains.MOI_TAO)
	String trangThai;
}
