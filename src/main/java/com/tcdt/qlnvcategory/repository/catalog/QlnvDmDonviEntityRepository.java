package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmDonvi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tcdt.qlnvcategory.entities.catalog.*;

@Repository
public interface QlnvDmDonviEntityRepository extends CrudRepository<QlnvDmDonviEntity, Long> {
	String value = "SELECT t.id,t.ma_dvi,t.ma_dvi_cha,(select ten_dvi from DM_DONVI where ma_dvi = t.ma_dvi_cha ) as TEN_DVI_CHA, t.TEN_DVI, t.MA_HCHINH, "
			+ "(select ten_dbhc from dm_dbhc where t.ma_tinh = ma_dbhc) as MA_TINH, "
			+ "(select ten_dbhc from dm_dbhc where t.ma_quan = ma_dbhc) as MA_QUAN, "
			+ "(select ten_dbhc from dm_dbhc where t.ma_phuong = ma_dbhc) as MA_PHUONG, "
			+ "t.dia_chi, t.cap_dvi, t.kieu_dvi, t.loai_dvi, t.ghi_chu, t.trang_thai, t.ngay_tao,"
			+ "t.nguoi_tao, t.ngay_sua, t.nguoi_sua FROM DM_DONVI t WHERE (:maDvi is null or lower(t.MA_DVI) like lower(concat(concat('%', :maDvi),'%'))) "
			+ "AND (:tenDvi is null or lower(t.TEN_DVI) like lower(concat(concat('%', :tenDvi),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai) "
			+ "AND (:maTinh is null or t.MA_TINH = :maTinh) AND (:maQuan is null or t.MA_QUAN = :maQuan) AND (:maPhuong is null or t.MA_PHUONG = :maPhuong) "
			+ "AND (:capDvi is null or t.CAP_DVI = :capDvi) AND (:kieuDvi is null or t.KIEU_DVI = :kieuDvi) AND (:loaiDvi is null or t.LOAI_DVI = :loaiDvi)";
	String countQuery = "SELECT count(1) FROM DM_DONVI t "
			+ "WHERE (:maDvi is null or lower(t.MA_DVI) like lower(concat(concat('%', :maDvi),'%'))) "
			+ "AND (:tenDvi is null or lower(t.TEN_DVI) like lower(concat(concat('%', :tenDvi),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai) "
			+ "AND (:maTinh is null or t.MA_TINH = :maTinh) AND (:maQuan is null or t.MA_QUAN = :maQuan) AND (:maPhuong is null or t.MA_PHUONG = :maPhuong) "
			+ "AND (:capDvi is null or t.CAP_DVI = :capDvi) AND (:kieuDvi is null or t.KIEU_DVI = :kieuDvi) AND (:loaiDvi is null or t.LOAI_DVI = :loaiDvi)";

	@Query(value = value, countQuery = countQuery, nativeQuery = true)
	Page<QlnvDmDonviEntity> selectParams(String maDvi, String tenDvi, String trangThai, String maTinh, String maQuan,
			String maPhuong, String capDvi, String kieuDvi, String loaiDvi, Pageable pageable);

	@Query(value = value, nativeQuery = true)
	Iterable<QlnvDmDonviEntity> finDvi(String maDvi, String tenDvi, String trangThai, String maTinh, String maQuan,
			String maPhuong, String capDvi, String kieuDvi, String loaiDvi);

	String value1 = "SELECT t.id,t.ma_dvi,t.ma_dvi_cha,(select ten_dvi from DM_DONVI where ma_dvi = t.ma_dvi_cha ) as TEN_DVI_CHA, t.TEN_DVI, t.MA_HCHINH, "
			+ "(select ten_dbhc from dm_dbhc where t.ma_tinh = ma_dbhc) as MA_TINH, "
			+ "(select ten_dbhc from dm_dbhc where t.ma_quan = ma_dbhc) as MA_QUAN, "
			+ "(select ten_dbhc from dm_dbhc where t.ma_phuong = ma_dbhc) as MA_PHUONG, "
			+ "t.dia_chi, t.cap_dvi, t.kieu_dvi, t.loai_dvi, t.ghi_chu, t.trang_thai, t.ngay_tao,"
			+ "t.nguoi_tao, t.ngay_sua, t.nguoi_sua FROM DM_DONVI t WHERE (:maDvi is null or t.ma_dvi_cha  =:maDvi) "
			+ " AND (:trangThai is null or t.TRANG_THAI = :trangThai) "
			+ "AND (:maTinh is null or t.MA_TINH = :maTinh) AND (:maQuan is null or t.MA_QUAN = :maQuan) AND (:maPhuong is null or t.MA_PHUONG = :maPhuong) "
			+ "AND (:capDvi is null or t.CAP_DVI = :capDvi) AND (:kieuDvi is null or t.KIEU_DVI = :kieuDvi) AND (:loaiDvi is null or t.LOAI_DVI = :loaiDvi)";

	@Query(value = value1, nativeQuery = true)
	List<QlnvDmDonviEntity> selectParamsChild(String maDvi, String trangThai, String maTinh, String maQuan,
			String maPhuong, String capDvi, String kieuDvi, String loaiDvi);

	String value2 = "SELECT t.id,t.ma_dvi,t.ma_dvi_cha,'' TEN_DVI_CHA, t.TEN_DVI, t.MA_HCHINH, "
			+ "'' as MA_TINH, "
			+ "'' as MA_QUAN, "
			+ "'' as MA_PHUONG, "
			+ "t.dia_chi, t.cap_dvi, t.kieu_dvi, t.loai_dvi, t.ghi_chu, t.trang_thai, t.ngay_tao,"
			+ "t.nguoi_tao, t.ngay_sua, t.nguoi_sua, " +
			" '' ma_Qd , '' ma_Tr , '' ma_khqlh , '' ma_ktbq , '' ma_tckt , '' ma_qhns FROM DM_DONVI t WHERE t.ma_dvi_cha  =:maDvi"
			+ " AND (:trangThai is null or t.TRANG_THAI = :trangThai) ";
	@Query(value = value2, nativeQuery = true)
	Iterable<QlnvDmDonviEntity> findByMaDviChaAndTrangThai(String maDvi, String trangThai);
}
