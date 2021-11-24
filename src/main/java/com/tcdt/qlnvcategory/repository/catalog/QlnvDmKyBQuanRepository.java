package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmKyBQuan;

@Repository
public interface QlnvDmKyBQuanRepository extends CrudRepository<QlnvDmKyBQuan, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_KY_BQUAN t WHERE (:maKy is null or lower(t.MA_KY) like lower(concat(concat('%', :maKy),'%'))) "
			+ "AND (:tenKy is null or lower(t.TEN_KY) like lower(concat(concat('%', :tenKy),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_KY_BQUAN t "
					+ "WHERE (:maKy is null or lower(t.MA_KY) like lower(concat(concat('%', :maKy),'%'))) "
					+ "AND (:tenKy is null or lower(t.TEN_KY) like lower(concat(concat('%', :tenKy),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmKyBQuan> selectParams(String maKy, String tenKy, String trangThai, Pageable pageable);

	QlnvDmKyBQuan findBymaKy(String maKy);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM QLNV_DM_KY_BQUAN u WHERE u.ID in ?1", nativeQuery = true)
	int deleteWithIds(List<Long> items);

}
