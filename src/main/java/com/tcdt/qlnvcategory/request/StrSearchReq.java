package com.tcdt.qlnvcategory.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StrSearchReq {
	@NotNull(message = "Không được để trống")
	String str;
}
