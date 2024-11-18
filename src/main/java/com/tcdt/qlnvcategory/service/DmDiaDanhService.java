package com.tcdt.qlnvcategory.service;

import com.tcdt.qlnvcategory.repository.catalog.DanhMucDiaDanhRepository;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmDiaDanhSearch;
import com.tcdt.qlnvcategory.table.diadanh_hanhchinh.DanhMucDiaDanh;
import com.tcdt.qlnvcategory.util.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DmDiaDanhService {
    @Autowired
    private DanhMucDiaDanhRepository danhMucDiaDanhRepository;

    public List<DanhMucDiaDanh> findAllByCapAndTrangThai(QlnvDmDiaDanhSearch req) {
        List<DanhMucDiaDanh> list = danhMucDiaDanhRepository.findAllByTrangThai(req.getCapDiaDanh(), Contains.TT_HOAT_DONG);
        return list;
    }
}
