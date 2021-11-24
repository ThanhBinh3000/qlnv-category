package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmDviTinhReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã đơn vị tính không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "DVT_1")
	String maDviTinh;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên vật tư không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên đơn vị tính 1")
	String tenDviTinh;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Ký hiệu không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "KH1")
	String kyHieu;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Đơn vị đo không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "kg")
	String dviDo;
	
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
