package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmNhapxuat;

@Repository
public interface QlnvDmNhapxuatRepository extends CrudRepository<QlnvDmNhapxuat, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_NHAPXUAT t WHERE (:maLhinh is null or lower(t.MA_LHINH) like lower(concat(concat('%', :maLhinh),'%'))) "
			+ "AND (:tenLhinh is null or lower(t.TEN_LHINH) like lower(concat(concat('%', :tenLhinh),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_NHAPXUAT t "
					+ "WHERE (:maLhinh is null or lower(t.MA_LHINH) like lower(concat(concat('%', :maLhinh),'%'))) "
					+ "AND (:tenLhinh is null or lower(t.TEN_LHINH) like lower(concat(concat('%', :tenLhinh),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmNhapxuat> selectParams(String maLhinh, String tenLhinh, String trangThai, Pageable pageable);

	QlnvDmNhapxuat findByMaLhinh(String maLhinh);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM QLNV_DM_NHAPXUAT u WHERE u.ID in ?1", nativeQuery = true)
	int deleteWithIds(List<Long> items);

	Iterable<QlnvDmNhapxuat> findByTrangThai(String hoatDong);

	Iterable<QlnvDmNhapxuat> findByLoaiAndTrangThai(String loai, String hoatDong);

}
