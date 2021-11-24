package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmMsoVtu;

@Repository
public interface QlnvDmMsoVtuRepository extends CrudRepository<QlnvDmMsoVtu, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_MSO_VTU t WHERE (:maMsoVtu is null or lower(t.MA_MSO_VTU) like lower(concat(concat('%', :maMsoVtu),'%'))) "
			+ "AND (:tenMsoVtu is null or lower(t.TEN_MSO_VTU) like lower(concat(concat('%', :tenMsoVtu),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_MSO_VTU t "
					+ "WHERE (:maMsoVtu is null or lower(t.MA_MSO_VTU) like lower(concat(concat('%', :maMsoVtu),'%'))) "
					+ "AND (:tenMsoVtu is null or lower(t.TEN_MSO_VTU) like lower(concat(concat('%', :tenMsoVtu),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmMsoVtu> selectParams(String maMsoVtu, String tenMsoVtu, String trangThai, Pageable pageable);

	QlnvDmMsoVtu findByMaMsoVtu(String maMsoVtu);

}
