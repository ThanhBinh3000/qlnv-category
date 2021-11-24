package com.tcdt.qlnvcategory.request.search.catalog;

import com.tcdt.qlnvcategory.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModuleSearchReq extends BaseRequest {
	String name;
	String status;
	String url;
	Long place;
	String isShow;
	String data;
	Long parentId;
}
