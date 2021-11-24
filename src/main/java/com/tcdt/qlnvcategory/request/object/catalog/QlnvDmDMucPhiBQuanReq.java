package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmDMucPhiBQuanReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	private Long id;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Cấp đơn vị không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "CAP1")
	String capDvi;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Nhóm bảo quản không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "NHOM1")
	String nhomBquan;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên định mức phí bảo quản không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên định mức")
	String tenDinhmuc;
	
	String maDviTinh;
	double mucPhi;
	
	@Size(max = 250, message = "Ghi chú không được vượt quá 250 ký tự")
	String ghiChu;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
