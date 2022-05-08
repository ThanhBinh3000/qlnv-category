package com.tcdt.qlnvcategory.table.catalog;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DM_NHAPXUAT")
@Data
public class QlnvDmNhapxuat {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QLNV_DM_COMMON_SEQ")
	@SequenceGenerator(sequenceName = "QLNV_DM_COMMON_SEQ", allocationSize = 1, name = "QLNV_DM_COMMON_SEQ")
	private Long id;
	String maLhinh;
	String tenLhinh;
	String ghiChu;
	String trangThai;
	Date ngayTao;
	String nguoiTao;
	Date ngaySua;
	String nguoiSua;
	String loai;
	String qdinhGia;
}
