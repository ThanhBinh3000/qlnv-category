package com.tcdt.qlnvcategory.service;

import com.tcdt.qlnvcategory.repository.catalog.DanhMucRepository;
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmDungChungReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmDungChungSearchReq;
import com.tcdt.qlnvcategory.table.catalog.QlnvDanhMuc;
import com.tcdt.qlnvcategory.util.PaginationSet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DanhMucDungChungService extends BaseService {

    @Autowired
    private DanhMucRepository repository;

    public Iterable<QlnvDanhMuc> getByLoaiAndTrangThaiOrderByThuTuHienThi(String loai, String trangThai) {
        return repository.findByLoaiAndTrangThaiOrderByThuTuHienThi(loai, trangThai);
    }

    public Iterable<QlnvDanhMuc> getAll() {
        return repository.findAll();
    }


    public Page<QlnvDanhMuc> getAll(QlnvDmDungChungSearchReq req) {
        try {
            int page = PaginationSet.getPage(req.getPaggingReq().getPage());
            int limit = PaginationSet.getLimit(req.getPaggingReq().getLimit());
            Pageable pageable = PageRequest.of(page, limit);

            Page<QlnvDanhMuc> result = repository.selectParams(req.getMa(), req.getMaCha(), req.getTrangThai(),
                    req.getGiaTri(), req.getLoai(), pageable);

            return result;
        } catch (
                Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public QlnvDanhMuc getById(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new UnsupportedOperationException("Không tồn tại bản ghi");
        }
        return repository.findById(id).get();

    }

    public QlnvDanhMuc save(QlnvDmDungChungReq objReq, HttpServletRequest req) throws Exception {
        QlnvDanhMuc dataMap = new ModelMapper().map(objReq, QlnvDanhMuc.class);
        if (repository.findByMa(objReq.getMa()) != null) {
            throw new UnsupportedOperationException("Mã danh mục chung đã tồn tại");
        }
        dataMap.setNguoiTao(getUserName(req));
        dataMap.setNgayTao(new Date());
        QlnvDanhMuc createCheck = repository.save(dataMap);

        return createCheck;
    }

    public QlnvDanhMuc update(QlnvDmDungChungReq objReq, HttpServletRequest req) throws Exception {
        Optional<QlnvDanhMuc> qOptional = repository.findById(objReq.getId());

        if (!qOptional.isPresent()) {
            throw new UnsupportedOperationException("Không tồn tại bản ghi");
        }

        QlnvDanhMuc dataDTB = qOptional.get();
        QlnvDanhMuc dataMap = new ModelMapper().map(objReq, QlnvDanhMuc.class);

        updateObjectToObject(dataDTB, dataMap);
        dataDTB.setNguoiSua(getUserName(req));
        dataDTB.setNgaySua(new Date());
        QlnvDanhMuc createCheck = repository.save(dataDTB);
        return createCheck;
    }

    public void delete(Long id) {
        Optional<QlnvDanhMuc> qOptional = repository.findById(id);
        if (!qOptional.isPresent()) {
            throw new UnsupportedOperationException("Không tồn tại bản ghi");
        }
        repository.delete(qOptional.get());
    }


}
