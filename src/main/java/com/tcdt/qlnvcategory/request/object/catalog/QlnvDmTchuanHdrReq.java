package com.tcdt.qlnvcategory.request.object.catalog;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcdt.qlnvcategory.util.Contains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QlnvDmTchuanHdrReq {
	@ApiModelProperty(notes = "Bắt buộc set đối với update")
	Long id;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Mã hàng không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "010201")
	String maHang;

	@NotNull(message = "Không được để trống")
	@Size(max = 20, message = "Tên quy chuẩn không được vượt quá 20 ký tự")
	@ApiModelProperty(example = "ISO2002")
	String tenQchuan;

	@ApiModelProperty(example = "2022")
	Long namQchuan;

	@NotNull(message = "Không được để trống")
	@Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
	@ApiModelProperty(example = Contains.HOAT_DONG)
	String trangThai;

	List<QlnvDmTchuanDtlReq> details;
}
