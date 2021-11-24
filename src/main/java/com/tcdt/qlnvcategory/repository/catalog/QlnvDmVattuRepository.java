package com.tcdt.qlnvcategory.repository.catalog;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmVattu;

@Repository
public interface QlnvDmVattuRepository extends CrudRepository<QlnvDmVattu, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_VATTU t WHERE (:maVtu is null or lower(t.MA_VTU) like lower(concat(concat('%', :maVtu),'%'))) "
			+ "AND (:tenVtu is null or lower(t.TEN_VTU) like lower(concat(concat('%', :tenVtu),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_VATTU t "
					+ "WHERE (:maVtu is null or lower(t.MA_VTU) like lower(concat(concat('%', :maVtu),'%'))) "
					+ "AND (:tenVtu is null or lower(t.TEN_VTU) like lower(concat(concat('%', :tenVtu),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmVattu> selectParams(String maVtu, String tenVtu, String trangThai, Pageable pageable);

	QlnvDmVattu findByMaVtu(String maVtu);
	
	List<QlnvDmVattu> findByTrangThai(String trangThai);

}
