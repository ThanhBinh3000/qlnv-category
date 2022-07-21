package com.tcdt.qlnvcategory.repository.catalog;

import com.tcdt.qlnvcategory.entities.catalog.QlnvDmDonviEntity;
import com.tcdt.qlnvcategory.entities.catalog.QlnvDmDonviTree;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmDonvi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QlnvDmDonviTreeRepository extends CrudRepository<QlnvDmDonviTree, Long> {
	@Query(value = "SELECT * FROM DM_DONVI DVI " +
			" WHERE ((:maDvi IS NULL AND DVI.MA_DVI_CHA IS NULL) OR DVI.MA_DVI = :maDvi) " +
			" AND DVI.TRANG_THAI = '01'", nativeQuery = true)
	List<QlnvDmDonviTree> selectAllTree(String maDvi);
}
