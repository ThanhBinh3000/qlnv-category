package com.tcdt.qlnvcategory.table.catalog;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "DM_DUNG_CHUNG")
@Data
public class QlnvDanhMuc implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DUNG_CHUNG_SEQ")
	@SequenceGenerator(sequenceName = "DM_DUNG_CHUNG_SEQ", allocationSize = 1, name = "DM_DUNG_CHUNG_SEQ")
	private Long id;

	String ma;
	String maCha;
	String giaTri;
	String ghiChu;
	String trangThai;
	@Transient
	String tenTrangThai;
	String nguoiTao;
	Date ngayTao;
	String nguoiSua;
	Date ngaySua;
	String loai;
	Integer thuTuHienThi;

}
