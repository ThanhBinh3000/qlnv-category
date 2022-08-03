package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDanhMuc;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface DanhMucRepository extends CrudRepository<QlnvDanhMuc, Long> {

    @Query(value = "SELECT * FROM DM_DUNG_CHUNG dm " +
            "WHERE (:ma IS NULL OR LOWER(dm.MA) LIKE LOWER(CONCAT(CONCAT('%',:ma),'%'))) " +
            "AND   (:maCha IS NULL OR LOWER(dm.MA_CHA) LIKE LOWER(CONCAT(CONCAT('%',:maCha),'%'))) " +
            "AND   (:trangThai IS NULL OR LOWER(dm.TRANG_THAI) = :trangThai)" +
            "AND   (:giaTri IS NULL OR LOWER(dm.GIA_TRI) = :giaTri) " +
            "AND   (:loai IS NULL OR LOWER(dm.LOAI) = :loai)",
            nativeQuery = true)
    Page<QlnvDanhMuc> selectPage(String ma, String maCha, String trangThai, String giaTri, String loai, Pageable pageable);
    Iterable<QlnvDanhMuc> findByTrangThai(String trangThai);

    Iterable<QlnvDanhMuc> findByLoaiAndTrangThaiOrderByThuTuHienThi(String loai, String trangThai);

    QlnvDanhMuc findByMa(String ma);

    Optional<QlnvDanhMuc> findById(Long id);

    @Transactional
    @Modifying
    void deleteAllByIdIn(List<Long> ids);


}
