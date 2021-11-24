package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmKieuKelot;

@Repository
public interface QlnvDmKieuKelotRepository extends CrudRepository<QlnvDmKieuKelot, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_KIEU_KELOT t WHERE (:maKieuKelot is null or lower(t.MA_KIEU_KELOT) like lower(concat(concat('%', :maKieuKelot),'%'))) "
			+ "AND (:tenKieuKelot is null or lower(t.TEN_KIEU_KELOT) like lower(concat(concat('%', :tenKieuKelot),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_KIEU_KELOT t "
					+ "WHERE (:maKieuKelot is null or lower(t.MA_KIEU_KELOT) like lower(concat(concat('%', :maKieuKelot),'%'))) "
					+ "AND (:tenKieuKelot is null or lower(t.TEN_KIEU_KELOT) like lower(concat(concat('%', :tenKieuKelot),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmKieuKelot> selectParams(String maKieuKelot, String tenKieuKelot, String trangThai, Pageable pageable);

	QlnvDmKieuKelot findByMaKieuKelot(String maKieuKelot);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM QLNV_DM_KIEU_KELOT u WHERE u.ID in ?1", nativeQuery = true)
	int deleteWithIds(List<Long> items);

}
