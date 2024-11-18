package com.tcdt.qlnvcategory.request.search.catalog;

import lombok.Data;

import java.util.List;

@Data
public class ListDviReq {
    List<String> type;

    String maDvi;
}
