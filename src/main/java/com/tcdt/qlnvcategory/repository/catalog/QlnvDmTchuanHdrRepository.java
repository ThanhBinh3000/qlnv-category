package com.tcdt.qlnvcategory.repository.catalog;

import org.springframework.stereotype.Repository;

import com.tcdt.qlnvcategory.repository.BaseRepository;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmTchuanHdr;

@Repository
public interface QlnvDmTchuanHdrRepository extends BaseRepository<QlnvDmTchuanHdr, Long> {

	QlnvDmTchuanHdr findByMaHang(String maHang);

}
