package com.tcdt.qlnvcategory.table.catalog;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "DM_TCHUAN_DTL")
@Data
public class QlnvDmTchuanDtl {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QLNV_DM_COMMON_SEQ")
	@SequenceGenerator(sequenceName = "QLNV_DM_COMMON_SEQ", allocationSize = 1, name = "QLNV_DM_COMMON_SEQ")
	private Long id;

	String tenTchuan;
	String chiSoNhap;
	String chiSoXuat;
	String danhMuc;
	String kieu;
	Integer thuTu;
	String phuongPhap;
	String trangThai;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tcHdrId")
	@JsonBackReference
	private QlnvDmTchuanHdr parent;
}
