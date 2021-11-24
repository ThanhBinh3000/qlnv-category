package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmDviCuutroReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã vật tư không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "KHO_2")
	String maDviCuutro;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên vật tư không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên loại kho 2")
	String tenDviCuutro;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
