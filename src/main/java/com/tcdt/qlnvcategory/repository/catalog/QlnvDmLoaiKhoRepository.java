package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmLoaiKho;

@Repository
public interface QlnvDmLoaiKhoRepository extends CrudRepository<QlnvDmLoaiKho, Long> {
	@Query(value = "SELECT * FROM DM_LOAI_KHO t WHERE (:maLhKho is null or lower(t.MA_LH_KHO) like lower(concat(concat('%', :maLhKho),'%'))) "
			+ "AND (:tenLhKho is null or lower(t.TEN_LH_KHO) like lower(concat(concat('%', :tenLhKho),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM DM_LOAI_KHO t "
					+ "WHERE (:maLhKho is null or lower(t.MA_LH_KHO) like lower(concat(concat('%', :maLhKho),'%'))) "
					+ "AND (:tenLhKho is null or lower(t.TEN_LH_KHO) like lower(concat(concat('%', :tenLhKho),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmLoaiKho> selectParams(String maLhKho, String tenLhKho, String trangThai, Pageable pageable);
	
	@Query(value = "SELECT * FROM DM_LOAI_KHO t WHERE (:maLhKho is null or lower(t.MA_LH_KHO) like lower(concat(concat('%', :maLhKho),'%'))) "
			+ "AND (:tenLhKho is null or lower(t.TEN_LH_KHO) like lower(concat(concat('%', :tenLhKho),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Iterable<QlnvDmLoaiKho> selectAll(String maLhKho, String tenLhKho, String trangThai);

	QlnvDmLoaiKho findByMaLhKho(String maLhKho);

}
