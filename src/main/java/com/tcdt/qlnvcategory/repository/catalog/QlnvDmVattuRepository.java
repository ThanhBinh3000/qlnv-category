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

	@Query(value = "SELECT * FROM DM_VATTU WHERE (:maCha is null or MA_CHA = :maCha) AND (:cap is null or cap = :cap) AND TRANG_THAI = :hoatDong order by ma", nativeQuery = true)
	Iterable<QlnvDmVattu> findByMaChaCus(String maCha, String cap, String hoatDong);

	Iterable<QlnvDmVattu> findByCapAndTrangThai(String cap, String hoatDong);


	@Query(value = "SELECT * FROM DM_VATTU WHERE MA_CHA is null AND TRANG_THAI = '01' AND DVI_QLY = 'TCDT' ", nativeQuery = true)
	Iterable<QlnvDmVattu> findParent();

	@Query(value = "SELECT * FROM DM_VATTU WHERE CHON = '01' AND LOAI_HANG= 'VT' AND TRANG_THAI = '01'", nativeQuery = true)
	Iterable<QlnvDmVattu> findVatTu();

}
