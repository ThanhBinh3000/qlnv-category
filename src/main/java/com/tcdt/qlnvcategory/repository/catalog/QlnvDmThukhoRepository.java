package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmThukho;

@Repository
public interface QlnvDmThukhoRepository extends CrudRepository<QlnvDmThukho, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_THUKHO t WHERE (:maThukho is null or lower(t.MA_THUKHO) like lower(concat(concat('%', :maThukho),'%'))) "
			+ "AND (:tenThukho is null or lower(t.TEN_THUKHO) like lower(concat(concat('%', :tenThukho),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_THUKHO t "
					+ "WHERE (:maThukho is null or lower(t.MA_THUKHO) like lower(concat(concat('%', :maThukho),'%'))) "
					+ "AND (:tenThukho is null or lower(t.TEN_THUKHO) like lower(concat(concat('%', :tenThukho),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmThukho> selectParams(String maThukho, String tenThukho, String trangThai, Pageable pageable);

	QlnvDmThukho findByMaThukho(String maThukho);

}
