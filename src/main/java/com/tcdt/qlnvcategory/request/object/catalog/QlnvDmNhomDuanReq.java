package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmNhomDuanReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã nhóm dự án không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "DA_1")
	String maNhom;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên nhóm dự án không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên nhóm dự án 1")
	String tenNhom;
	
	@Size(max = 250, message = "Ghi chú không được vượt quá 250 ký tự")
	String ghiChu;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
