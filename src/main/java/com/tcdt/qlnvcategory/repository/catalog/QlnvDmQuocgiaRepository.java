package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmQuocgia;

@Repository
public interface QlnvDmQuocgiaRepository extends CrudRepository<QlnvDmQuocgia, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_QUOCGIA t WHERE (:maQgia is null or lower(t.MA_QGIA) like lower(concat(concat('%', :maQgia),'%'))) "
			+ "AND (:tenQgia is null or lower(t.TEN_QGIA) like lower(concat(concat('%', :tenQgia),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_QUOCGIA t "
					+ "WHERE (:maQgia is null or lower(t.MA_QGIA) like lower(concat(concat('%', :maQgia),'%'))) "
					+ "AND (:tenQgia is null or lower(t.TEN_QGIA) like lower(concat(concat('%', :tenQgia),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmQuocgia> selectParams(String maQgia, String tenQgia, String trangThai, Pageable pageable);

	QlnvDmQuocgia findByMaQgia(String maQgia);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM QLNV_DM_QUOCGIA u WHERE u.ID in ?1", nativeQuery = true)
	int deleteWithIds(List<Long> items);

}
