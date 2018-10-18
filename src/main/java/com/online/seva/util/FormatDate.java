package com.online.seva.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FormatDate {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy");

    public String getFormatDate(String formatDate) {


        Date date = null;
        try {
            date = sdf.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String format = dateFormat.format(date);
        //System.out.println("formatted date : " + format);
        return format;
    }

    public Date getDateFromFormat(String format) {
        Date date = null;
        try {
            date = dateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println("formatted date : " + format);
        return date;
    }
}
