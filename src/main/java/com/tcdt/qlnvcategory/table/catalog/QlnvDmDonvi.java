package com.tcdt.qlnvcategory.table.catalog;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name = "DM_DONVI")
@Data
public class QlnvDmDonvi implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DONVI_SEQ")
	@SequenceGenerator(sequenceName = "DM_DONVI_SEQ", allocationSize = 1, name = "DM_DONVI_SEQ")
	private Long id;
	@Transient
	String key;
	@Transient
	String title;
	@Transient
	boolean isLeaf = false;

	String maDvi;
	String maDviCha;
	String tenDvi;
	String maHchinh;
	String maTinh;
	String maQuan;
	String maPhuong;
	String diaChi;
	String capDvi;
	String kieuDvi;
	String loaiDvi;
	String ghiChu;
	String trangThai;
	Date ngayTao;
	String nguoiTao;
	Date ngaySua;
	String nguoiSua;
	String maQd;
	String maTr;
	String maKhqlh;
	String maKtbq;
	String maTckt;
	String maQhns;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "maDviCha", referencedColumnName = "maDvi")
	@JsonIgnore
	private List<QlnvDmDonvi> children;

	public String getKey() {
		return maDvi;
	}

	public String getTitle() {
		return tenDvi;
	}

	public boolean isIsLeaf() {
		return children == null || children.isEmpty();
	}

}
