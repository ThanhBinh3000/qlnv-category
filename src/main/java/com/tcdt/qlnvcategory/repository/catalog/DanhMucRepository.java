package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.tcdt.qlnvcategory.table.catalog.QlnvDanhMuc;
import javax.transaction.Transactional;
import java.util.Optional;

public interface DanhMucRepository extends CrudRepository<QlnvDanhMuc, Long> {

    @Query(value = "SELECT * FROM DM_DUNG_CHUNG DM " +
            "WHERE (:ma IS NULL OR LOWER(DM.MA) LIKE LOWER(CONCAT(CONCAT('%',:ma),'%'))) " +
            "AND   (:maCha IS NULL OR LOWER(DM.MA_CHA) LIKE LOWER(CONCAT(CONCAT('%',:maCha),'%'))) " +
            "AND   (:trangThai IS NULL OR  LOWER ( " +
            "    CASE WHEN DM.TRANG_THAI = '01' then 'Hoạt động' " +
            "    WHEN DM.TRANG_THAI = '00' then 'Không hoạt động' " +
            "    ELSE 'Không hoạt động' end" +
            " ) LIKE LOWER(CONCAT(CONCAT('%',:trangThai),'%')))" +
            "AND   (:giaTri IS NULL OR LOWER(DM.GIA_TRI) LIKE LOWER(CONCAT(CONCAT('%',:giaTri),'%'))) " +
            "AND   (:loai IS NULL OR LOWER(DM.LOAI) LIKE LOWER(CONCAT(CONCAT('%',:loai),'%')))",
            nativeQuery = true)
    Page<QlnvDanhMuc> selectPage(String ma, String maCha, String trangThai, String giaTri, String loai, Pageable pageable);
    Iterable<QlnvDanhMuc> findByTrangThai(String trangThai);

    Iterable<QlnvDanhMuc> findByLoaiAndTrangThaiOrderByThuTuHienThi(String loai, String trangThai);

    QlnvDanhMuc findByMa(String ma);

    Optional<QlnvDanhMuc> findById(Long id);

    @Transactional
    @Modifying
    @Query(value= "UPDATE DM_DUNG_CHUNG pl SET pl.TRANG_THAI ='00' WHERE pl.ID=?",nativeQuery = true)
    int  softDelete(Long ids);


}
