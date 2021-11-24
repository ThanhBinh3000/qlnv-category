package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmDviLquanReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã đơn vị liên quan không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "DVLQ_2")
	String maDvi;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên đơn vị liên quan không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên đơn vị 2")
	String tenDvi;
	
	@Size(max = 20, message = "Mã hành chính không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "MHC_2")
	String maHchinh;
	
	@Size(max = 250, message = "Địa chỉ đơn vị liên quan không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Địa chỉ đơn vị 2")
	String diaChi;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
