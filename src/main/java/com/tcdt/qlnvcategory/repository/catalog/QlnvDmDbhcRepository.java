package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmDbhc;

@Repository
public interface QlnvDmDbhcRepository extends CrudRepository<QlnvDmDbhc, Long> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM QLNV_DM_DBHC u WHERE u.ID in ?1", nativeQuery = true)
	int deleteWithIds(List<Long> items);

	QlnvDmDbhc findByMaDbhc(String maDbhc);

	@Query(value = "SELECT * FROM QLNV_DM_DBHC t WHERE (:maDbhc is null or lower(MA_DBHC) like lower(concat(concat('%', :maDbhc),'%'))) "
			+ "AND (:tenDbhc is null or lower(TEN_DBHC) like lower(concat(concat('%', :tenDbhc),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_DBHC t "
					+ "WHERE (:maDbhc is null or lower(MA_DBHC) like lower(concat(concat('%', :maDbhc),'%'))) "
					+ "AND (:tenDbhc is null or lower(TEN_DBHC) like lower(concat(concat('%', :tenDbhc),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmDbhc> selectParams(String maDbhc, String tenDbhc, String trangThai, Pageable pageable);

	Iterable<QlnvDmDbhc> findByCapAndTrangThai(String cap, String trangThai);

	@Query(value = "SELECT * FROM QLNV_DM_DBHC WHERE (:maCha is null or MA_CHA = :maCha) AND TRANG_THAI = :hoatDong order by id", nativeQuery = true)
	Iterable<QlnvDmDbhc> findByMaChaCus(String maCha, String hoatDong);

}
