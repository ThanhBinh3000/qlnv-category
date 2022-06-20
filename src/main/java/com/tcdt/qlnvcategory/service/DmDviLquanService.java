package com.tcdt.qlnvcategory.service;

import com.tcdt.qlnvcategory.repository.catalog.QlnvDmDviLquanRepository;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmDviLquanSearchReq;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmDviLquan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DmDviLquanService extends BaseService {
	@Autowired
	private QlnvDmDviLquanRepository repository;

	public List<QlnvDmDviLquan> getAll(QlnvDmDviLquanSearchReq objReq){
		return repository.selectAll(objReq.getTypeDvi(),objReq.getTrangThai());
	}

}