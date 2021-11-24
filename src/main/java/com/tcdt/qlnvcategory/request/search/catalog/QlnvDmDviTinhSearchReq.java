package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmDviTinhSearchReq extends BaseRequest {
	@ApiModelProperty(example = "DVT_1")
	String maDviTinh;
	@ApiModelProperty(example = "Tên đơn vị tính 1")
	String tenDviTinh;
	@ApiModelProperty(example = "KH1")
	String kyHieu;
	@ApiModelProperty(example = "kg")
	String dviDo;
}
