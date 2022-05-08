package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmDviTinh;

@Repository
public interface QlnvDmDviTinhRepository extends CrudRepository<QlnvDmDviTinh, Long> {
	@Query(value = "SELECT * FROM DM_DVI_TINH t WHERE (:maDviTinh is null or lower(t.MA_DVI_TINH) like lower(concat(concat('%', :maDviTinh),'%'))) "
			+ "AND (:tenDviTinh is null or lower(t.TEN_DVI_TINH) like lower(concat(concat('%', :tenDviTinh),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai) "
			+ "AND (:kyHieu is null or lower(t.KY_HIEU) like lower(concat(concat('%', :kyHieu),'%'))) AND (:dviDo is null or lower(t.DVI_DO) like lower(concat(concat('%', :dviDo),'%')))", countQuery = "SELECT count(1) FROM QLNV_DM_DVI_TINH t "
					+ "WHERE (:maDviTinh is null or lower(t.MA_DVI_TINH) like lower(concat(concat('%', :maDviTinh),'%'))) "
					+ "AND (:tenDviTinh is null or lower(t.TEN_DVI_TINH) like lower(concat(concat('%', :tenDviTinh),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai) "
					+ "AND (:kyHieu is null or lower(t.KY_HIEU) like lower(concat(concat('%', :kyHieu),'%'))) AND (:dviDo is null or lower(t.DVI_DO) like lower(concat(concat('%', :dviDo),'%')))", nativeQuery = true)
	Page<QlnvDmDviTinh> selectParams(String maDviTinh, String tenDviTinh, String kyHieu, String dviDo, String trangThai,
			Pageable pageable);

	QlnvDmDviTinh findByMaDviTinh(String maDviTinh);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM QLNV_DM_DVI_TINH u WHERE u.ID in ?1", nativeQuery = true)
	int deleteWithIds(List<Long> ids);

	Iterable<QlnvDmDviTinh> findByTrangThai(String hoatDong);

}
