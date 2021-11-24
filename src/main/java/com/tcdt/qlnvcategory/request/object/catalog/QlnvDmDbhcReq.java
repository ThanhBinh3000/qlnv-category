package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmDbhcReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;

	@NotNull(message = "Không được để trống")
	@Size(max = 5, message = "Mã địa bàn hành chính không được vượt quá 5 ký tự")
	String maDbhc;

	@Size(max = 250, message = "Tên địa bàn hành chính không được vượt quá 250 ký tự")
	String tenDbhc;

	@NotNull(message = "Không được để trống")
	@Size(max = 5, message = "Mã cha không được vượt quá 5 ký tự")
	String maCha;

	@Size(max = 20, message = "Mã hành chính không được được vượt quá 20 ký tự")
	String maHchinh;

	@NotNull(message = "Không được để trống")
	@Size(max = 1, message = "Cấp không được vượt quá 1 ký tự")
	String cap;

	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
