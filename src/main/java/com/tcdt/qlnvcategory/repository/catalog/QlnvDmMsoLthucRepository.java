package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmMsoLthuc;

@Repository
public interface QlnvDmMsoLthucRepository extends CrudRepository<QlnvDmMsoLthuc, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_MSO_LTHUC t WHERE (:maMsoLthuc is null or lower(t.MA_MSO_LTHUC) like lower(concat(concat('%', :maMsoLthuc),'%'))) "
			+ "AND (:tenMsoLthuc is null or lower(t.TEN_MSO_LTHUC) like lower(concat(concat('%', :tenMsoLthuc),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_MSO_LTHUC t "
					+ "WHERE (:maMsoLthuc is null or lower(t.MA_MSO_LTHUC) like lower(concat(concat('%', :maMsoLthuc),'%'))) "
					+ "AND (:tenMsoLthuc is null or lower(t.TEN_MSO_LTHUC) like lower(concat(concat('%', :tenMsoLthuc),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmMsoLthuc> selectParams(String maMsoLthuc, String tenMsoLthuc, String trangThai, Pageable pageable);

	QlnvDmMsoLthuc findByMaMsoLthuc(String maMsoLthuc);

}
