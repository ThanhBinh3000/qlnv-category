package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmPthucDthau;

@Repository
public interface QlnvDmPthucDthauRepository extends CrudRepository<QlnvDmPthucDthau, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_PTHUC_DTHAU t WHERE (:maPthuc is null or lower(t.MA_PTHUC) like lower(concat(concat('%', :maPthuc),'%'))) "
			+ "AND (:tenPthuc is null or lower(t.TEN_PTHUC) like lower(concat(concat('%', :tenPthuc),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_PTHUC_DTHAU t "
					+ "WHERE (:maPthuc is null or lower(t.MA_PTHUC) like lower(concat(concat('%', :maPthuc),'%'))) "
					+ "AND (:tenPthuc is null or lower(t.TEN_PTHUC) like lower(concat(concat('%', :tenPthuc),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmPthucDthau> selectParams(String maPthuc, String tenPthuc, String trangThai, Pageable pageable);

	QlnvDmPthucDthau findByMaPthuc(String maPthuc);

}
