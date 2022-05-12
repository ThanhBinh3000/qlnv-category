package com.tcdt.qlnvcategory.util;

import com.tcdt.qlnvcategory.service.SecurityContextService;
import com.tcdt.qlnvcategory.table.UserInfo;

public class UserUtils {
	public static UserInfo getUserInfo() throws Exception {
		UserInfo userInfo = SecurityContextService.getUser();
		if (userInfo == null) throw new Exception("Can not get user info");

		return userInfo;
	}

}
