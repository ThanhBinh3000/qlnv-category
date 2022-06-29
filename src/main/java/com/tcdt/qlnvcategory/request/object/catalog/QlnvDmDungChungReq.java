package com.tcdt.qlnvcategory.request.object.catalog;

import com.tcdt.qlnvcategory.util.Contains;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class QlnvDmDungChungReq {
    @ApiModelProperty(notes = "Bắt buộc set đối với update")
    Long id;

    @NotNull(message = "Không được để trống")
    String ma;

    @Size(max = 20, message = "Mã cha không được vượt quá 20 ký tự")
    @ApiModelProperty(example = "DV_1")
    String maCha;

    @NotNull(message = "Không được để trống")
    String giaTri;

    @Size(max = 250, message = "Ghi chú không được vượt quá 250 ký tự")
    String ghiChu;

    @NotNull(message = "Không được để trống")
    @Size(max = 2, message = "Trạng thái không được vượt quá 2 ký tự")
    @ApiModelProperty(example = Contains.HOAT_DONG)
    String trangThai;

    @NotNull(message = "Không được để trống")
    String loai;

}
