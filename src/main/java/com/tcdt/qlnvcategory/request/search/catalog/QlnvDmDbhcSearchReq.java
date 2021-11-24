package com.tcdt.qlnvcategory.request.search.catalog;



import com.tcdt.qlnvcategory.request.BaseRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QlnvDmDbhcSearchReq extends BaseRequest{
	String maDbhc;
	String tenDbhc;
}
