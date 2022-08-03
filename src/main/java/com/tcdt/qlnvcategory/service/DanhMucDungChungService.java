package com.tcdt.qlnvcategory.service;

import com.tcdt.qlnvcategory.repository.catalog.DanhMucRepository;
import com.tcdt.qlnvcategory.request.PaggingReq;
import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmDungChungReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmDungChungSearchReq;
import com.tcdt.qlnvcategory.table.catalog.DmPhanLoaiDc;
import com.tcdt.qlnvcategory.table.catalog.QlnvDanhMuc;
//import com.tcdt.qlnvcategory.util.ExportExcel;
import com.tcdt.qlnvcategory.util.PaginationSet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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


    public Page<QlnvDanhMuc> searchPage(QlnvDmDungChungSearchReq objReq) {
            Pageable pageable= PageRequest.of(objReq.getPaggingReq().getPage(),
                    objReq.getPaggingReq().getLimit(), Sort.by("id").ascending());
            Page<QlnvDanhMuc> data = repository.selectPage(
                    objReq.getMa(),
                    objReq.getMaCha(),
                    objReq.getTrangThai(),
                    objReq.getGiaTri(),
                    objReq.getLoai(),
                    pageable);
            return data;
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

    public void deleteListId(List<Long> listId){
        repository.deleteAllByIdIn(listId);
    }

    public void exportList(QlnvDmDungChungSearchReq objReq, HttpServletResponse response) throws Exception{
        PaggingReq paggingReq = new PaggingReq();
        paggingReq.setPage(0);
        paggingReq.setLimit(Integer.MAX_VALUE);
        objReq.setPaggingReq(paggingReq);
        Page<QlnvDanhMuc> page=this.searchPage(objReq);
        List<QlnvDanhMuc> data=page.getContent();

        String title="Danh sách danh mục dùng chung";
        String[] rowsName=new String[]{"STT","Vùng dữ liệu/ Loại danh mục dùng chung","Mã","Giá trị","Trạng thái","Người tạo","Ngày tạo","Người sửa","Ngày sửa"};
        String fileName="danh-sach-danh-muc-dung-chung.xlsx";
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs=null;
        for (int i=0;i<data.size();i++){
            QlnvDanhMuc dx=data.get(i);
            objs=new Object[rowsName.length];
            objs[0]=i;
            objs[1]=dx.getLoai();
            objs[2]=dx.getMa();
            objs[3]=dx.getGiaTri();
            objs[4]=dx.getTrangThai();
            objs[5]=dx.getNguoiTao();
            objs[6]=dx.getNgayTao();
            objs[7]=dx.getNguoiSua();
            objs[8]=dx.getNgaySua();
            dataList.add(objs);

        }
//        ExportExcel ex =new ExportExcel(title,fileName,rowsName,dataList,response);
//        ex.export();
    }

}
