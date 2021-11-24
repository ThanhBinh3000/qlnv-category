package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmMsoVtuReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã số vật tư không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "MSVT_1")
	String maMsoVtu;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên vật tư không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên mã số vật tư 1")
	String tenMsoVtu;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Chủng loại không được vượt quá 2 ký tự")
	@ApiModelProperty(example = "LV")
	String loaiVtu;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Nhóm vật tư không được vượt quá 2 ký tự")
	@ApiModelProperty(example = "AB")
	String nhomVtu;
	
	@Size(max = 250, message = "Ghi chú không được vượt quá 250 ký tự")
	String ghiChu;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
