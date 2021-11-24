package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmDmucCcuReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Cấp đơn vị không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "VT_1")
	String capDvi;

	@Size(max = 20, message = "Nhóm công cụ không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "CC1")
	String nhomCcu;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Loại nhập xuất không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "NX_1")
	String loaiNhapxuat;

	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên định mức không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên định mức")
	String tenDinhmuc;

	@Min(value = 1, message = "Số lượng phải lớn hơn 1")
	Long soluong;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã đơn vị tính không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "DV_1")
	String maDviTinh;

	Long dongia;

	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
