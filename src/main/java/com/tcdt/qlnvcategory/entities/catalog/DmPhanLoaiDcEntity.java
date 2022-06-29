package com.tcdt.qlnvcategory.entities.catalog;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class DmPhanLoaiDcEntity {
    @Id
    String ghiChu;
    String maPloai;
    String tenPloai;
    Date ngaySua;
    Date ngayTao;
    String nguoiSua;
    String nguoiTao;
    String trangThai;
}
