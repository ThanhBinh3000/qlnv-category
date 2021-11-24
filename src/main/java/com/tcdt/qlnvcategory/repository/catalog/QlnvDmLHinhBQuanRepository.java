package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmLHinhBQuan;

public interface QlnvDmLHinhBQuanRepository extends CrudRepository<QlnvDmLHinhBQuan, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_LHINH_BQUAN t WHERE (:maLHinh is null or lower(t.MA_LHINH) like lower(concat(concat('%', :maLHinh),'%'))) AND (:tenLHinh is null or lower(t.TEN_LHINH) like lower(concat(concat('%', :tenLHinh),'%'))) "
			+ " AND (:trangThai is null or t.TRANG_THAI = :trangThai)", 
			countQuery = "SELECT count(1) FROM QLNV_DM_LHINH_BQUAN t "
					+ "WHERE (:maLHinh is null or lower(t.MA_LHINH) like lower(concat(concat('%', :maLHinh),'%'))) AND  (:tenLHinh is null or lower(t.TEN_LHINH) like lower(concat(concat('%', :tenLHinh),'%'))) "
					+ " AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmLHinhBQuan> selectParams(String maLHinh, String tenLHinh, String trangThai, Pageable pageable);
}
