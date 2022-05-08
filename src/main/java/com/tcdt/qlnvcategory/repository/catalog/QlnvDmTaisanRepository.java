package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmTaisan;

@Repository
public interface QlnvDmTaisanRepository extends CrudRepository<QlnvDmTaisan, Long> {
	@Query(value = "select t.id, t.ma_taisan, t.ten_taisan, t.mo_ta, (select ten_dvi_tinh from DM_DVI_TINH where t.ma_dvi_tinh = ma_dvi_tinh) as ma_dvi_tinh,"
			+ "t.so_luong, (select ten_dvi from DM_DONVI where t.ma_dvi = ma_dvi) as ma_dvi, t.ngay_sdung,"
			+ "t.ghi_chu, t.trang_thai, t.ngay_tao, t.nguoi_tao, t.ngay_sua, t.nguoi_sua from DM_TAISAN t "
			+ "WHERE (:maTaisan is null or lower(t.MA_TAISAN) like lower(concat(concat('%', :maTaisan),'%'))) "
			+ "AND (:tenTaisan is null or lower(t.TEN_TAISAN) like lower(concat(concat('%', :tenTaisan),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM DM_TAISAN t "
					+ "WHERE (:maTaisan is null or lower(t.MA_TAISAN) like lower(concat(concat('%', :maTaisan),'%'))) "
					+ "AND (:tenTaisan is null or lower(t.TEN_TAISAN) like lower(concat(concat('%', :tenTaisan),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmTaisan> selectParams(String maTaisan, String tenTaisan, String trangThai, Pageable pageable);

	QlnvDmTaisan findByMaTaisan(String maTaisan);

}
