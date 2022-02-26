package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.repository.BaseRepository;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmVattu;

@Repository
public interface QlnvDmVattuRepository extends BaseRepository<QlnvDmVattu, Long> {
	QlnvDmVattu findByMa(String ma);

	List<QlnvDmVattu> findByTrangThai(String trangThai);

	@Query(value = "SELECT * FROM QLNV_DM_VATTU WHERE (:maCha is null or MA_CHA = :maCha) AND TRANG_THAI = :hoatDong order by ma", nativeQuery = true)
	Iterable<QlnvDmVattu> findByMaChaCus(String maCha, String hoatDong);

}
