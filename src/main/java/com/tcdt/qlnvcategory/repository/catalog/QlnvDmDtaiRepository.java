package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmDtai;

public interface QlnvDmDtaiRepository extends CrudRepository<QlnvDmDtai, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_DETAI t WHERE (:maDmDt is null or lower(t.MA_DM_DT) like lower(concat(concat('%', :maDmDt),'%'))) AND (:tenDmDt is null or lower(t.TEN_DM_DT) like lower(concat(concat('%', :tenDmDt),'%'))) "
			+ " AND (:trangThai is null or t.TRANG_THAI = :trangThai)", 
			countQuery = "SELECT count(1) FROM QLNV_DM_DETAI t "
					+ "WHERE (:maDmDt is null or lower(t.MA_DM_DT) like lower(concat(concat('%', :maDmDt),'%'))) AND  (:tenDmDt is null or lower(t.TEN_DM_DT) like lower(concat(concat('%', :tenDmDt),'%'))) "
					+ " AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmDtai> selectParams(String maDmDt, String tenDmDt, String trangThai, Pageable pageable);
}
