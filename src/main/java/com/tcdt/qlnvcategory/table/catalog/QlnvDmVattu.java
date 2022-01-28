package com.tcdt.qlnvcategory.table.catalog;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "QLNV_DM_VATTU")
@Data
public class QlnvDmVattu {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QLNV_DM_COMMON_SEQ")
	@SequenceGenerator(sequenceName = "QLNV_DM_COMMON_SEQ", allocationSize = 1, name = "QLNV_DM_COMMON_SEQ")
	private Long id;

	String ghiChu;
	String ma;
	Date ngaySua;
	Date ngayTao;
	String nguoiSua;
	String nguoiTao;
	String ten;
	String trangThai;
	String maDviTinh;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "maCha", referencedColumnName = "ma")
	private QlnvDmVattu parent;

}
