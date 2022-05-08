package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmDviCuutro;

@Repository
public interface QlnvDmDviCuutroRepository extends CrudRepository<QlnvDmDviCuutro, Long> {
	@Query(value = "SELECT * FROM DM_DVI_CUUTRO t WHERE (:maDviCuutro is null or lower(t.MA_DVI_CUUTRO) like lower(concat(concat('%', :maDviCuutro),'%'))) "
			+ "AND (:tenDviCuutro is null or lower(t.TEN_DVI_CUUTRO) like lower(concat(concat('%', :tenDviCuutro),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM DM_DVI_CUUTRO t "
					+ "WHERE (:maDviCuutro is null or lower(t.MA_DVI_CUUTRO) like lower(concat(concat('%', :maDviCuutro),'%'))) "
					+ "AND (:tenDviCuutro is null or lower(t.TEN_DVI_CUUTRO) like lower(concat(concat('%', :tenDviCuutro),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmDviCuutro> selectParams(String maDviCuutro, String tenDviCuutro, String trangThai, Pageable pageable);

	QlnvDmDviCuutro findByMaDviCuutro(String maDviCuutro);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM DM_DVI_CUUTRO u WHERE u.ID in ?1", nativeQuery = true)
	int deleteWithIds(List<Long> ids);

}
