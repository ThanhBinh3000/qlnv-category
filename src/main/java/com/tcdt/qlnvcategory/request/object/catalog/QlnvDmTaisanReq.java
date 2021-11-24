package com.tcdt.qlnvcategory.request.object.catalog;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmTaisanReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã quốc gia sản xuất không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "QG_1")
	String maTaisan;

	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên quốc gia sản xuất không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên quốc gia sản xuất 1")
	String tenTaisan;

	String moTa;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã đơn vị tính không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "DVT_1")
	String maDviTinh;

	@Min(value = 1, message = "Số lượng phải lớn hơn 1")
	Long soLuong;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã đơn vị không được vượt quá 20 ký tự")
	String maDvi;

	@NotNull(message = "Không được để trống")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Past
	Date ngaySdung;

	@Size(max = 250, message = "Ghi chú không được vượt quá 250 ký tự")
	String ghiChu;

	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
