package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmNhomDuan;

@Repository
public interface QlnvDmNhomDuanRepository extends CrudRepository<QlnvDmNhomDuan, Long> {
	@Query(value = "SELECT * FROM DM_NHOM_DUAN t WHERE (:maNhom is null or lower(t.MA_NHOM) like lower(concat(concat('%', :maNhom),'%'))) "
			+ "AND (:tenNhom is null or lower(t.TEN_NHOM) like lower(concat(concat('%', :tenNhom),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM DM_NHOM_DUAN t "
					+ "WHERE (:maNhom is null or lower(t.MA_NHOM) like lower(concat(concat('%', :maNhom),'%'))) "
					+ "AND (:tenNhom is null or lower(t.TEN_NHOM) like lower(concat(concat('%', :tenNhom),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmNhomDuan> selectParams(String maNhom, String tenNhom, String trangThai, Pageable pageable);

	QlnvDmNhomDuan findByMaNhom(String maNhom);

}
