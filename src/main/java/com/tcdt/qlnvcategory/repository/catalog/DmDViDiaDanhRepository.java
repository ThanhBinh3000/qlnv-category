package com.tcdt.qlnvcategory.repository.catalog;

import com.tcdt.qlnvcategory.table.catalog.DmDonViDiaDanh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DmDViDiaDanhRepository extends JpaRepository<DmDonViDiaDanh, Long> {
    void deleteAllByIdDvi(Long id);

    List<DmDonViDiaDanh> findAllByIdDvi(Long idDvi);
}
