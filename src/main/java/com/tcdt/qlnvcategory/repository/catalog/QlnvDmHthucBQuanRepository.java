package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmHthucBQuan;

public interface QlnvDmHthucBQuanRepository extends CrudRepository<QlnvDmHthucBQuan, Long> {
	@Query(value = "SELECT * FROM DM_HTHUC_BQUAN t WHERE (:maHthuc is null or lower(t.MA_HTHUC) like lower(concat(concat('%', :maHthuc),'%'))) AND (:tenHthuc is null or lower(t.TEN_HTHUC) like lower(concat(concat('%', :tenHthuc),'%'))) "
			+ " AND (:trangThai is null or t.TRANG_THAI = :trangThai)", 
			countQuery = "SELECT count(1) FROM DM_HTHUC_BQUAN t "
					+ "WHERE (:maHthuc is null or lower(t.MA_HTHUC) like lower(concat(concat('%', :maHthuc),'%'))) AND  (:tenHthuc is null or lower(t.TEN_HTHUC) like lower(concat(concat('%', :tenHthuc),'%'))) "
					+ " AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmHthucBQuan> selectParams(String maHthuc, String tenHthuc, String trangThai, Pageable pageable);
}
