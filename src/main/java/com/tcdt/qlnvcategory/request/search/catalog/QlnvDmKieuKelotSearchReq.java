package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmKieuKelotSearchReq extends BaseRequest{
	@ApiModelProperty(example = "KL_1")
	String maKieuKelot;
	@ApiModelProperty(example = "Tên kiểu kê lót 1")
	String tenKieuKelot;
}
