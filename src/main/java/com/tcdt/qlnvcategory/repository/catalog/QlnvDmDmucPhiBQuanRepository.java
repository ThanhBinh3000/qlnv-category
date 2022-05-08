package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmDMucPhiBQuan;

public interface QlnvDmDmucPhiBQuanRepository extends CrudRepository<QlnvDmDMucPhiBQuan, Long> {
	@Query(value = "SELECT * FROM DM_DMUC_PHI t WHERE (:tenDMuc is null or lower(t.TEN_DINHMUC) like lower(concat(concat('%', :tenDMuc),'%'))) "
			+ " AND (:nhomBQuan is null or lower(t.NHOM_BQUAN) like lower(concat(concat('%', :nhomBQuan),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", 
			countQuery = "SELECT count(1) FROM DM_DMUC_PHI t "
					+ "WHERE (:tenDMuc is null or lower(t.TEN_DINHMUC) like lower(concat(concat('%', :tenDMuc),'%'))) "
					+ "AND (:nhomBQuan is null or lower(t.NHOM_BQUAN) like lower(concat(concat('%', :nhomBQuan),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmDMucPhiBQuan> selectParams(String nhomBQuan, String tenDMuc, String trangThai, Pageable pageable);
}
