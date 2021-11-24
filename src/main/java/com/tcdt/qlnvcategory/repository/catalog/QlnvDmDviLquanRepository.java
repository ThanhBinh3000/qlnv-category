package com.tcdt.qlnvcategory.repository.catalog;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmDviLquan;

@Repository
public interface QlnvDmDviLquanRepository extends CrudRepository<QlnvDmDviLquan, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_DVI_LQUAN t WHERE (:maDvi is null or lower(t.MA_DVI) like lower(concat(concat('%', :maDvi),'%'))) "
			+ "AND (:tenDvi is null or lower(t.TEN_DVI) like lower(concat(concat('%', :tenDvi),'%'))) AND (:maHchinh is null or lower(t.MA_HCHINH) like lower(concat(concat('%', :maHchinh),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_DVI_LQUAN t "
					+ "WHERE (:maDvi is null or lower(t.MA_DVI) like lower(concat(concat('%', :maDvi),'%'))) "
					+ "AND (:tenDvi is null or lower(t.TEN_DVI) like lower(concat(concat('%', :tenDvi),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmDviLquan> selectParams(String maDvi, String tenDvi, String maHchinh, String trangThai, Pageable pageable);

	QlnvDmDviLquan findByMaDvi(String maDvi);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM QLNV_DM_DVI_LQUAN u WHERE u.ID in ?1", nativeQuery = true)
	int deleteWithIds(List<Long> ids);

}
