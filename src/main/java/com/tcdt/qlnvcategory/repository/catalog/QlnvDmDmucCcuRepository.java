package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmDmucCcu;

@Repository
public interface QlnvDmDmucCcuRepository extends CrudRepository<QlnvDmDmucCcu, Long> {
	@Query(value = "SELECT t.ID,t.CAP_DVI,(select ten_ccu from qlnv_dm_congcu where ma_ccu = t.NHOM_CCU) NHOM_CCU,(select ten_lhinh from qlnv_dm_nhapxuat where ma_lhinh = t.LOAI_NHAPXUAT) LOAI_NHAPXUAT,"
			+ "t.TEN_DINHMUC,t.SOLUONG,(select ten_dvi_tinh from qlnv_dm_dvi_tinh where ma_dvi_tinh = t.MA_DVI_TINH) MA_DVI_TINH,"
			+ "t.DONGIA,t.TRANG_THAI,t.NGAY_TAO,t.NGUOI_TAO,t.NGAY_SUA, t.NGUOI_SUA "
			+ "FROM QLNV_DM_DMUC_CCU t WHERE (:tenDinhmuc is null or lower(t.TEN_DINHMUC) like lower(concat(concat('%', :tenDinhmuc),'%'))) "
			+ "AND (:capDvi is null or t.CAP_DVI = :capDvi) AND (:nhomCcu is null or t.NHOM_CCU = :nhomCcu) AND (:loaiNhapxuat is null or t.LOAI_NHAPXUAT = :loaiNhapxuat) "
			+ "AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_DMUC_CCU t "
					+ "WHERE (:tenDinhmuc is null or lower(t.TEN_DINHMUC) like lower(concat(concat('%', :tenDinhmuc),'%'))) "
					+ "AND (:capDvi is null or t.CAP_DVI = :capDvi) AND (:nhomCcu is null or t.NHOM_CCU = :nhomCcu) AND (:loaiNhapxuat is null or t.LOAI_NHAPXUAT = :loaiNhapxuat) "
					+ "AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmDmucCcu> selectParams(String capDvi, String nhomCcu, String loaiNhapxuat, String tenDinhmuc,
			String trangThai, Pageable pageable);

}
