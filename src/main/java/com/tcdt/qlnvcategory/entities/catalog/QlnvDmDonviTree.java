package com.tcdt.qlnvcategory.entities.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcdt.qlnvcategory.table.RolesPermission;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class QlnvDmDonviTree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

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
	@JoinColumn(name = "maDviCha", referencedColumnName = "maDvi",insertable=false, updatable=false)
	private List<QlnvDmDonviTree> children;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "maDviCha", referencedColumnName = "maDvi",insertable=false, updatable=false)
	private QlnvDmDonviTree parent;


}
