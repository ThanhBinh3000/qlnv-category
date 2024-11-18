package com.tcdt.qlnvcategory.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HandleDateFormat {
    public static Date convertDate(String dateString) throws ParseException {
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sourceFormat.parse(dateString);
        return date;
    }
}
