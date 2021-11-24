package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmTchuan;

@Repository
public interface QlnvDmTchuanRepository extends CrudRepository<QlnvDmTchuan, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_TCHUAN t WHERE (:maTchuan is null or lower(t.MA_TCHUAN) like lower(concat(concat('%', :maTchuan),'%'))) "
			+ "AND (:tenTchuan is null or lower(t.TEN_TCHUAN) like lower(concat(concat('%', :tenTchuan),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_TCHUAN t "
					+ "WHERE (:maTchuan is null or lower(t.MA_TCHUAN) like lower(concat(concat('%', :maTchuan),'%'))) "
					+ "AND (:tenTchuan is null or lower(t.TEN_TCHUAN) like lower(concat(concat('%', :tenTchuan),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmTchuan> selectParams(String maTchuan, String tenTchuan, String trangThai, Pageable pageable);

	QlnvDmTchuan findByMaTchuan(String maTchuan);

	Iterable<QlnvDmTchuan> findByTrangThai(String hoatDong);

}
