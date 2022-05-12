package com.tcdt.qlnvcategory.service;

import com.tcdt.qlnvcategory.jwt.CustomUserDetails;
import com.tcdt.qlnvcategory.table.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextService {
	public static UserInfo getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
			return userDetails.getUser();
		}

		return null;
	}
}
