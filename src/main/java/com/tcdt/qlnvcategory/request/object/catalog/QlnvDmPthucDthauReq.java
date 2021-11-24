package com.tcdt.qlnvcategory.request.object.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmPthucDthauReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã phương thức đấu thầu không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "PT_1")
	String maPthuc;

	@NotNull(message = "Không được để trống")
	@Size(max = 250, message = "Tên phương thức đấu thầu không được vượt quá 250 ký tự")
	@ApiModelProperty(example = "Tên phương thức đấu thầu 1")
	String tenPthuc;

	@Size(max = 250, message = "Ghi chú không được vượt quá 250 ký tự")
	String ghiChu;

	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;
}
