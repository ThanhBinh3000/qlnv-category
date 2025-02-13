package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.entities.catalog.*;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmDonvi;

@Repository
public interface QlnvDmDonviRepository extends CrudRepository<QlnvDmDonvi, Long> {
	@Query(value = "SELECT t.id,t.ma_dvi,(select ten_dvi from DM_DONVI where ma_dvi = t.ma_dvi_cha ) as MA_DVI_CHA, t.TEN_DVI, t.MA_HCHINH, "
			+ "(select ten_dbhc from dm_dbhc where t.ma_tinh = ma_dbhc) as MA_TINH, "
			+ "(select ten_dbhc from dm_dbhc where t.ma_quan = ma_dbhc) as MA_QUAN, "
			+ "(select ten_dbhc from dm_dbhc where t.ma_phuong = ma_dbhc) as MA_PHUONG, "
			+ "t.dia_chi, t.cap_dvi, t.kieu_dvi, t.loai_dvi, t.ghi_chu, t.trang_thai, t.ngay_tao,"
			+ "t.nguoi_tao, t.ngay_sua, t.nguoi_sua FROM DM_DONVI t WHERE (:maDvi is null or lower(t.MA_DVI) like lower(concat(concat('%', :maDvi),'%'))) "
			+ "AND (:tenDvi is null or lower(t.TEN_DVI) like lower(concat(concat('%', :tenDvi),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai) "
			+ "AND (:maTinh is null or t.MA_TINH = :maTinh) AND (:maQuan is null or t.MA_QUAN = :maQuan) AND (:maPhuong is null or t.MA_PHUONG = :maPhuong) "
			+ "AND (:capDvi is null or t.CAP_DVI = :capDvi) AND (:kieuDvi is null or t.KIEU_DVI = :kieuDvi) AND (:loaiDvi is null or t.LOAI_DVI = :loaiDvi)", countQuery = "SELECT count(1) FROM DM_DONVI t "
					+ "WHERE (:maDvi is null or lower(t.MA_DVI) like lower(concat(concat('%', :maDvi),'%'))) "
					+ "AND (:tenDvi is null or lower(t.TEN_DVI) like lower(concat(concat('%', :tenDvi),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai) "
					+ "AND (:maTinh is null or t.MA_TINH = :maTinh) AND (:maQuan is null or t.MA_QUAN = :maQuan) AND (:maPhuong is null or t.MA_PHUONG = :maPhuong) "
					+ "AND (:capDvi is null or t.CAP_DVI = :capDvi) AND (:kieuDvi is null or t.KIEU_DVI = :kieuDvi) AND (:loaiDvi is null or t.LOAI_DVI = :loaiDvi)", nativeQuery = true)
	Page<QlnvDmDonviEntity> selectParams(String maDvi, String tenDvi, String trangThai, String maTinh, String maQuan,
			String maPhuong, String capDvi, String kieuDvi, String loaiDvi, Pageable pageable);

	Optional<QlnvDmDonvi>  findByMaDvi(String maDvi);

	//Iterable<QlnvDmDonvi> findByMaDviChaAndTrangThai(String dvql, String trangThai);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM DM_DONVI u WHERE u.ID in ?1", nativeQuery = true)
	int deleteWithIds(List<Long> ids);

	@Query(value = "SELECT * " +
			" FROM DM_DONVI DV " +
			"WHERE DV.CAP_DVI < 4 AND DV.TRANG_THAI = :trangThai", nativeQuery = true)
	List<QlnvDmDonvi> findByTrangThai(String trangThai);

	Iterable<QlnvDmDonvi> findByMaDviChaIsNull();

	@Query(value = "SELECT * FROM DM_DONVI DVI " +
			" WHERE (:capDvi IS NULL OR DVI.CAP_DVI = TO_NUMBER(:capDvi)) " +
			" AND (:trangThai IS NULL OR DVI.TRANG_THAI = :trangThai)" +
			" AND (:maDviCha IS NULL OR DVI.MA_DVI_CHA = :maDviCha)", nativeQuery = true)
	List<QlnvDmDonvi> selectAll( String capDvi,String maDviCha, String trangThai);

	@Query(value = "SELECT * FROM DM_DONVI DVI " +
			" WHERE ((:maDvi IS NULL AND DVI.MA_DVI_CHA IS NULL) OR DVI.MA_DVI = :maDvi) " +
			" AND(:trangThai IS NULL OR DVI.TRANG_THAI = :trangThai) ", nativeQuery = true)
	List<QlnvDmDonvi> selectAllTree( String maDvi, String trangThai);
}
