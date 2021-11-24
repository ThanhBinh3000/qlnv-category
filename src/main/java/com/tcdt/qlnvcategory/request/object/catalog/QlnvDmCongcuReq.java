package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmCongcuReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã công cụ bảo quản không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "CC_1")
	String maCcu;

	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên công cụ bảo quản không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên công cụ bảo quản 1")
	String tenCcu;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã đơn vị tính không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "DV_1")
	String maDviTinh;

	@Size(max = 250, message = "Ghi chú không được vượt quá 250 ký tự")
	String ghiChu;

	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
