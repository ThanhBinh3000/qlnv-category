package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmTchuanDtlReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;


	@NotNull(message = "Không được để trống")
	@Size(max = 255, message = "Tên tiêu chuẩn không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "Tên tiêu chuẩn")
	String tenTchuan;

	@NotNull(message = "Không được để trống")
	@Size(max = 255, message = "Chỉ số nhập không được vượt quá 255 ký tự")
	@ApiModelProperty(example = "Chỉ số nhập")
	String chiSoNhap;

	@NotNull(message = "Không được để trống")
	@Size(max = 255, message = "Chỉ số xuất không được vượt quá 255 ký tự")
	@ApiModelProperty(example = "Chỉ số xuất")
	String chiSoXuat;
	
	@Size(max = 255, message = "Danh mục không được vượt quá 255 ký tự")
	@ApiModelProperty(example = "Danh mục")
	String danhMuc;

	@Size(max = 20, message = "Kiểu không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "Kiểu")
	String kieu;

	@ApiModelProperty(example = "1")
	Integer thuTu;
	
	@Size(max = 50, message = "Phương pháp không được vượt quá 50 ký tự")
	String phuongPhap;

	Long tcHdrId;

	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
