package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.data.repository.CrudRepository;

import com.tcdt.qlnvcategory.table.catalog.QlnvDanhMuc;

public interface DanhMucRepository extends CrudRepository<QlnvDanhMuc, Long> {

	Iterable<QlnvDanhMuc> findByTrangThai(String trangThai);

	Iterable<QlnvDanhMuc> findByLoaiAndTrangThai(String loai, String trangThai);

}
