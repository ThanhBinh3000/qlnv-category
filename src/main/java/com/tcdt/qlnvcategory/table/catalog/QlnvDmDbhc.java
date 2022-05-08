package com.tcdt.qlnvcategory.table.catalog;

import java.io.Serializable;
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
@Table(name = "DM_DBHC")
@Data
public class QlnvDmDbhc implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QLNV_DM_COMMON_SEQ")
	@SequenceGenerator(sequenceName = "QLNV_DM_COMMON_SEQ", allocationSize = 1, name = "QLNV_DM_COMMON_SEQ")
	private Long id;
	String maDbhc;
	String tenDbhc;
	String maHchinh;
//	String maCha;
	String cap;
	String trangThai;
	Date ngayTao;
	String nguoiTao;
	Date ngaySua;
	String nguoiSua;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "maCha", referencedColumnName = "maDbhc")
	private QlnvDmDbhc parent;

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "maCha", referencedColumnName = "maDbhc")
//	private List<QlnvDmDbhc> child;
}
