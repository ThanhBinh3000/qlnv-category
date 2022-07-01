package com.tcdt.qlnvcategory.table.catalog;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "DM_PHAN_LOAI_DC")

@Data
public class DmPhanLoaiDc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QLNV_DM_COMMON_SEQ")
    @SequenceGenerator(sequenceName = "QLNV_DM_COMMON_SEQ", allocationSize = 1, name = "QLNV_DM_COMMON_SEQ")
    private Long id;

    String ghiChu;
    String maPloai;
    String tenPloai;
    Date ngaySua;
    Date ngayTao;
    String nguoiSua;
    String nguoiTao;
    String trangThai;


}


