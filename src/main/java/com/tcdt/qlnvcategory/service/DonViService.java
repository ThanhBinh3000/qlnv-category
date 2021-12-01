package com.tcdt.qlnvcategory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tcdt.qlnvcategory.repository.catalog.QlnvDmDonviRepository;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmDonvi;

@Service
@CacheConfig(cacheNames = "dviCache")
public class DonViService {
	@Autowired
	private QlnvDmDonviRepository qlnvDmDonviRepository;

	@Cacheable
	public QlnvDmDonvi getDonViById(long maDvi) {
		Optional<QlnvDmDonvi> qOptional = qlnvDmDonviRepository.findById(maDvi);
		return qOptional.get();
	}
}
