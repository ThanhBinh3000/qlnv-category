package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDmTtrangGthau;

@Repository
public interface QlnvDmTtrangGthauRepository extends CrudRepository<QlnvDmTtrangGthau, Long> {
	@Query(value = "SELECT * FROM QLNV_DM_TTRANG_THAU t WHERE (:maTtrang is null or lower(t.MA_TTRANG) like lower(concat(concat('%', :maTtrang),'%'))) "
			+ "AND (:tenTtrang is null or lower(t.TEN_TTRANG) like lower(concat(concat('%', :tenTtrang),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", countQuery = "SELECT count(1) FROM QLNV_DM_TTRANG_THAU t "
					+ "WHERE (:maTtrang is null or lower(t.MA_TTRANG) like lower(concat(concat('%', :maTtrang),'%'))) "
					+ "AND (:tenTtrang is null or lower(t.TEN_TTRANG) like lower(concat(concat('%', :tenTtrang),'%'))) AND (:trangThai is null or t.TRANG_THAI = :trangThai)", nativeQuery = true)
	Page<QlnvDmTtrangGthau> selectParams(String maTtrang, String tenTtrang, String trangThai, Pageable pageable);

	QlnvDmTtrangGthau findByMaTtrang(String maTtrang);

}
