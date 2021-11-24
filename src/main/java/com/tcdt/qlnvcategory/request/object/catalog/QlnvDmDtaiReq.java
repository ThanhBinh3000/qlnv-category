package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmDtaiReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	private Long id;
	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên đề tài không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên đề tài")
	String tenDmDt;
	@Size(max = 20, message = "Mã đề tài không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "Mã đề tài")
	String maDmDt;
	@Size(max = 250, message = "Ghi chú không được vượt quá 250 ký tự")
	String ghiChu;
	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
