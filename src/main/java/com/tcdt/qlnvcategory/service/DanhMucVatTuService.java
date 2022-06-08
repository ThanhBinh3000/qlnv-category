package com.tcdt.qlnvcategory.service;

import com.tcdt.qlnvcategory.jwt.CustomUserDetails;
import com.tcdt.qlnvcategory.repository.UserActionRepository;
import com.tcdt.qlnvcategory.repository.UserHistoryRepository;
import com.tcdt.qlnvcategory.repository.UserInfoRepository;
import com.tcdt.qlnvcategory.repository.catalog.QlnvDmVattuRepository;
import com.tcdt.qlnvcategory.response.DMVatTuResponse;
import com.tcdt.qlnvcategory.table.UserAction;
import com.tcdt.qlnvcategory.table.UserHistory;
import com.tcdt.qlnvcategory.table.UserInfo;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmVattu;
import com.tcdt.qlnvcategory.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DanhMucVatTuService extends BaseService {
	@Autowired
	private QlnvDmVattuRepository repository;

	public List<DMVatTuResponse> getAllByDvql(){
		List<QlnvDmVattu> data = repository.findByDviQly();
		List<DMVatTuResponse> list = new ArrayList<>();
		if (data != null) {
			list = ObjectMapperUtils.mapAll(data, DMVatTuResponse.class);
		}
		return list;
	}

}