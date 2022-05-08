package com.tcdt.qlnvcategory.table.catalog;

import java.util.ArrayList;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "DM_TCHUAN_HDR")
@Data
@NamedEntityGraph(name = "DM_TCHUAN_HDR.children", attributeNodes = @NamedAttributeNode("children"))
public class QlnvDmTchuanHdr {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QLNV_DM_COMMON_SEQ")
	@SequenceGenerator(sequenceName = "QLNV_DM_COMMON_SEQ", allocationSize = 1, name = "QLNV_DM_COMMON_SEQ")
	private Long id;

	String maHang;
	String tenQchuan;
	Long namQchuan;
	String trangThai;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "tcHdrId")
	@JsonManagedReference
	private List<QlnvDmTchuanDtl> children = new ArrayList<>();

	public void setChildren(List<QlnvDmTchuanDtl> children) {
		this.children.clear();
		for (QlnvDmTchuanDtl child : children) {
			child.setParent(this);
		}
		this.children.addAll(children);
	}

	public void addChild(QlnvDmTchuanDtl child) {
		child.setParent(this);
		this.children.add(child);
	}
}
