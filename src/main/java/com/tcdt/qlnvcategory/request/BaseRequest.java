package com.tcdt.qlnvcategory.request;

import lombok.Data;

@Data
public class BaseRequest {
	public static final int DEFAULT_PAGE = 0;
	public static final int DEFAULT_LIMIT = 10;

	private PaggingReq paggingReq;
	private String trangThai;
	private String str;

	public PaggingReq getPaggingReq() {
		if (this.paggingReq == null) {
			this.paggingReq = new PaggingReq(DEFAULT_LIMIT, DEFAULT_PAGE);
		}
		return this.paggingReq;
	}
}