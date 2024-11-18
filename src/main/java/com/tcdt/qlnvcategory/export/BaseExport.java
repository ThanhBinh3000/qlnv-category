package com.tcdt.qlnvcategory.export;


import javax.servlet.http.HttpServletResponse;

public interface BaseExport {

    void setHeader(HttpServletResponse response);

    void writeDataLines() throws Exception;

    void writeHeaderLine();

}
