package com.tcdt.qlnvcategory.service;

import com.tcdt.qlnvcategory.repository.catalog.DmPhanLoaiDcRepository;
import com.tcdt.qlnvcategory.request.object.catalog.DmPhanLoaiDcReq;
import com.tcdt.qlnvcategory.request.search.catalog.DmPhanLoaiDcSearchReq;
import com.tcdt.qlnvcategory.table.catalog.DmPhanLoaiDc;
import com.tcdt.qlnvcategory.util.Contains;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class DmPhanLoaiDcService extends  BaseService {
    @Autowired
    private DmPhanLoaiDcRepository dmPhanLoaiDcRepository;

    public Iterable<DmPhanLoaiDc> findAll(@Valid DmPhanLoaiDcReq objReq) {
        return dmPhanLoaiDcRepository.findAll();
    }

    public Page<DmPhanLoaiDc> searchPage(DmPhanLoaiDcSearchReq objReq)throws Exception{
        Pageable pageable= PageRequest.of(objReq.getPaggingReq().getPage(),
                objReq.getPaggingReq().getLimit(),
                Sort.by("id").ascending());
        Page<DmPhanLoaiDc> data= dmPhanLoaiDcRepository.selectParams(
                objReq.getMaPloai(),
                objReq.getTenPloai(),
                objReq.getTrangThai(),
                pageable);
        return data;
    }

    public DmPhanLoaiDc save(DmPhanLoaiDcReq objReq, HttpServletRequest req) throws Exception{
        if (dmPhanLoaiDcRepository.findByMaPloai(objReq.getMaPloai()) != null) {
            throw new Exception("Mã phân loại đã tồn tại");
        }
            DmPhanLoaiDc dataMap=new ModelMapper().map(objReq,DmPhanLoaiDc.class);
            dataMap.setTrangThai(Contains.HOAT_DONG);
            dataMap.setNguoiTao(getUserName(req));
            dataMap.setNgayTao(new Date());
            DmPhanLoaiDc createCheck =dmPhanLoaiDcRepository.save(dataMap);
            return createCheck;
    }
    public DmPhanLoaiDc update(DmPhanLoaiDcReq objReq,HttpServletRequest req) throws Exception{
        Optional<DmPhanLoaiDc> qOptional = dmPhanLoaiDcRepository.findById(objReq.getId());
        if (!qOptional.isPresent())
            throw new UnsupportedOperationException("Mã phân loại dùng chung không tồn tại ");
        DmPhanLoaiDc dataDTB=qOptional.get();
        DmPhanLoaiDc datamap=new ModelMapper().map(objReq,DmPhanLoaiDc.class);
        updateObjectToObject(dataDTB,datamap);
        dataDTB.setNguoiSua(getUserName(req));
        dataDTB.setNgaySua(new Date());
        DmPhanLoaiDc createCheck=dmPhanLoaiDcRepository.save(dataDTB);
        return createCheck;
    }

    public void delete(Long ids){
        Optional<DmPhanLoaiDc>qOptional=dmPhanLoaiDcRepository.findById(ids);
        if (!qOptional.isPresent()){
            throw new UnsupportedOperationException("Id không tồn tại");
            
        }
        dmPhanLoaiDcRepository.delete(qOptional.get());
    }
    public DmPhanLoaiDc getById(String id) throws Exception{
        Optional<DmPhanLoaiDc> qOptional=dmPhanLoaiDcRepository.findById(Long.parseLong(id));
        if(!qOptional.isPresent()){
            throw new Exception("Danh mục phân loại dùng chung không tồn tại ");
        }
        return qOptional.get();
    }


    public int softDelete(Long ids) {
        return dmPhanLoaiDcRepository.softDelete(ids);
    }


    public List<DmPhanLoaiDc> selectTrangThai() {
        return dmPhanLoaiDcRepository.selectTrangThai();
    }
}
