package com.tcdt.qlnvcategory.table.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "QLNV_DM_VATTU")
@Data
@NamedEntityGraph(name = "QLNV_DM_VATTU.children", attributeNodes = @NamedAttributeNode("children"))
public class QlnvDmVattu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	String maCha;

//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
//	@JoinColumn(name = "maCha", referencedColumnName = "ma")
//	private QlnvDmVattu parent;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "maCha", referencedColumnName = "ma")
	@JsonManagedReference
	private List<QlnvDmVattu> children = new ArrayList<>();

}
