package com.tcdt.qlnvcategory.repository.catalog;

import com.tcdt.qlnvcategory.entities.catalog.DmPhanLoaiDcEntity;
import com.tcdt.qlnvcategory.table.catalog.DmPhanLoaiDc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository

public interface DmPhanLoaiDcRepository extends CrudRepository<DmPhanLoaiDc, Long> {
    DmPhanLoaiDc findByMaPloai(String maPloai);

    @Query(value = "SELECT * FROM DM_PHAN_LOAI_DC t WHERE (:maPloai is null or lower(t.MA_PLOAI) like lower(concat(concat('%', :maPloai),'%'))) "
            + "AND (:tenPloai is null or lower(t.TEN_PLOAI) like lower(concat(concat('%', :tenPloai),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM DM_PHAN_LOAI_DC t "
            + "WHERE (:maPloai is null or lower(t.MA_PLOAI) like lower(concat(concat('%', :maPloai),'%'))) "
            + "AND (:tenPloai is null or lower(t.TEN_PLOAI) like lower(concat(concat('%', :tenPloai),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
    Page<DmPhanLoaiDc> selectParams(String maPloai, String tenPloai, String trangThai, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value= "UPDATE DM_PHAN_LOAI_DC pl SET pl.TRANG_THAI ='00' WHERE pl.ID=?",nativeQuery = true)
    int  softDelete(Long ids);

    @Query(value = "SELECT * FROM DM_PHAN_LOAI_DC pl WHERE pl.TRANG_THAI='01'",nativeQuery = true)
    List<DmPhanLoaiDc> selectTrangThai();
}
