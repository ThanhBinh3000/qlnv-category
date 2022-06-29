package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDanhMuc;

import java.util.Optional;

public interface DanhMucRepository extends CrudRepository<QlnvDanhMuc, Long> {

    @Query(value = "SELECT * FROM DM_DUNG_CHUNG dm WHERE (:ma is null or lower(dm.MA) like lower(concat(concat('%', " +
            ":ma),'%'))) AND   (:maCha is null or lower(dm.MA_CHA) like lower(concat(concat('%', " + ":maCha),'%'))) " +
            "AND " + "(:trangThai is null or lower(dm.TRANG_THAI) = :trangThai) AND (:giaTri is null or lower(dm" +
            ".GIA_TRI) = " +
            ":giaTri) AND (:loai is null or lower(dm.LOAI) = :loai)",
            nativeQuery =
                    true)
    Page<QlnvDanhMuc> selectParams(String ma, String maCha, String trangThai, String giaTri, String loai, Pageable pageable);


    Iterable<QlnvDanhMuc> findByTrangThai(String trangThai);

    Iterable<QlnvDanhMuc> findByLoaiAndTrangThaiOrderByThuTuHienThi(String loai, String trangThai);

    QlnvDanhMuc findByMa(String ma);

    Optional<QlnvDanhMuc> findById(Long id);


}
