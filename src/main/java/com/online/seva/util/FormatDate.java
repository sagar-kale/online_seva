package com.online.seva.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
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
        //System.out.println("formatted date : " + format);
        return dateFormat.format(date);
    }

    public Date getDateFromFormat(String format) {
        AtomicReference<Date> date = null;
        try {
            date.set(dateFormat.parse(format));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println("formatted date : " + format);
        return date.get();
    }

    public boolean isJobEndDateExpired(String lastDate) {
        Date parsedLastDate = null;
        Date currentDate = null;//dateFormat.parse(dateFormat.format(dateFormat.parse(lastDate)));
        try {
            parsedLastDate = sdf.parse(lastDate);
            String curr_date_format = sdf.format(new Date());
            currentDate = sdf.parse(curr_date_format);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        log.info("parsed after format:::" + sdf.format(parsedLastDate));


        log.info("current date:::" + sdf.format(currentDate));

        String formattedString = String.format("job last date= %s is before current date=%s", sdf.format(parsedLastDate), sdf.format(currentDate));
        assert currentDate != null;
        if ((parsedLastDate != null) && parsedLastDate.before(currentDate)) {
            log.info(formattedString);
            log.info("job is expired...");
            return true;
        }
        return false;
    }
}
