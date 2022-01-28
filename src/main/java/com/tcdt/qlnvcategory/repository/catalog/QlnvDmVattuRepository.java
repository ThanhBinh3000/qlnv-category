package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.repository.BaseRepository;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmVattu;

@Repository
public interface QlnvDmVattuRepository extends BaseRepository<QlnvDmVattu, Long> {
	QlnvDmVattu findByMa(String ma);

	List<QlnvDmVattu> findByTrangThai(String trangThai);

}
