package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmHthucKtra;

@Repository
public interface QlnvDmHthucKtraRepository extends CrudRepository<QlnvDmHthucKtra, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_HTHUC_KTRA t WHERE (:maHthuc is null or lower(t.MA_HTHUC) like lower(concat(concat('%', :maHthuc),'%'))) "
			+ "AND (:tenHthuc is null or lower(t.TEN_HTHUC) like lower(concat(concat('%', :tenHthuc),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_HTHUC_KTRA t "
					+ "WHERE (:maHthuc is null or lower(t.MA_HTHUC) like lower(concat(concat('%', :maHthuc),'%'))) "
					+ "AND (:tenHthuc is null or lower(t.TEN_HTHUC) like lower(concat(concat('%', :tenHthuc),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmHthucKtra> selectParams(String maHthuc, String tenHthuc, String trangThai, Pageable pageable);

	QlnvDmHthucKtra findByMaHthuc(String maHthuc);

}
