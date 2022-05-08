package com.tcdt.qlnvcategory.table.catalog;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcdt.qlnvcategory.util.Contains;

import lombok.Data;

@Entity
@Table(name = "DM_TAISAN")
@Data
public class QlnvDmTaisan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QLNV_DM_COMMON_SEQ")
	@SequenceGenerator(sequenceName = "QLNV_DM_COMMON_SEQ", allocationSize = 1, name = "QLNV_DM_COMMON_SEQ")
	private Long id;
	String maTaisan;
	String tenTaisan;
	String moTa;
	String maDviTinh;
	Long soLuong;
	String maDvi;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Contains.FORMAT_DATE_STR)
	Date ngaySdung;
	String ghiChu;
	String trangThai;
	Date ngayTao;
	String nguoiTao;
	Date ngaySua;
	String nguoiSua;
}
