package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmBoNganh;

@Repository
public interface QlnvDmBoNganhRepository extends CrudRepository<QlnvDmBoNganh, Long> {
	@Query(value = "SELECT * FROM DM_BO_NGANH t WHERE (:ma is null or lower(t.MA) like lower(concat(concat('%', :ma),'%'))) "
			+ "AND (:ten is null or lower(t.TEN) like lower(concat(concat('%', :ten),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM DM_BO_NGANH t "
					+ "WHERE (:ma is null or lower(t.MA) like lower(concat(concat('%', :ma),'%'))) "
					+ "AND (:ten is null or lower(t.TEN) like lower(concat(concat('%', :ten),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmBoNganh> selectParams(String ma, String ten, String trangThai, Pageable pageable);

	QlnvDmBoNganh findByMa(String ma);

}
