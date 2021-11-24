package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmMsoLthucReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã số lương thực không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "MSVT_1")
	String maMsoLthuc;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên mã số lương thực không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên mã số lương thực 1")
	String tenMsoLthuc;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Chủng loại không được vượt quá 2 ký tự")
	@ApiModelProperty(example = "LV")
	String loaiLthuc;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Nhóm lương thực không được vượt quá 2 ký tự")
	@ApiModelProperty(example = "AB")
	String nhomLthuc;
	
	@Size(max = 250, message = "Ghi chú không được vượt quá 250 ký tự")
	String ghiChu;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
