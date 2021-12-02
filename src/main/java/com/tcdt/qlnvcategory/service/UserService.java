package com.tcdt.qlnvcategory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tcdt.qlnvcategory.jwt.CustomUserDetails;
import com.tcdt.qlnvcategory.repository.UserActionRepository;
import com.tcdt.qlnvcategory.repository.UserHistoryRepository;
import com.tcdt.qlnvcategory.repository.UserInfoRepository;
import com.tcdt.qlnvcategory.table.UserAction;
import com.tcdt.qlnvcategory.table.UserHistory;
import com.tcdt.qlnvcategory.table.UserInfo;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmDonvi;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserInfoRepository userRepository;
	@Autowired
	UserHistoryRepository userHistoryRepository;

	@Autowired
	UserActionRepository userActionRepository;

	@Autowired
	DonViService donViService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		// TODO:cache thong tin don vi cua user dang nhap
		QlnvDmDonvi abc = donViService.getDonViById(user.getDvql());
		System.out.println(abc);
		return new CustomUserDetails(user);
	}

	public Iterable<UserAction> findAll() {
		return userActionRepository.findAll();
	}

	public void saveUserHistory(UserHistory userHistory) {
		userHistoryRepository.save(userHistory);
	}

}