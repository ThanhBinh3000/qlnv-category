package com.tcdt.qlnvcategory.repository.catalog;

import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmVattuSearchReq;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmHangDvql;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface QlnvDmHangDvqlRepository extends CrudRepository<QlnvDmHangDvql, Long> {
    List<QlnvDmHangDvql> findAllByMaHangHoa(String maHangHoa);

    @Transactional
    void deleteAllByMaHangHoa(String maHangHoa);

    List<QlnvDmHangDvql> findAllByMaDvi (String maDvi);
}
