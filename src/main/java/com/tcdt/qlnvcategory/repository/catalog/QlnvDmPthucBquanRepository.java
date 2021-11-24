package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmPthucBquan;

@Repository
public interface QlnvDmPthucBquanRepository extends CrudRepository<QlnvDmPthucBquan, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_PTHUC_BQUAN t WHERE (:maPthuc is null or lower(t.MA_PTHUC) like lower(concat(concat('%', :maPthuc),'%'))) "
			+ "AND (:tenPthuc is null or lower(t.TEN_PTHUC) like lower(concat(concat('%', :tenPthuc),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_PTHUC_BQUAN t "
					+ "WHERE (:maPthuc is null or lower(t.MA_PTHUC) like lower(concat(concat('%', :maPthuc),'%'))) "
					+ "AND (:tenPthuc is null or lower(t.TEN_PTHUC) like lower(concat(concat('%', :tenPthuc),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmPthucBquan> selectParams(String maPthuc, String tenPthuc, String trangThai, Pageable pageable);

	QlnvDmPthucBquan findByMaPthuc(String maPthuc);

}
