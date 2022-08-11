package com.bruno.springmyproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    private DateUtil() {
        throw new IllegalStateException("Util class");
    }

    public static LocalDate convertStringToLocalDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedString = date.substring(0, 2) + "/" +
                date.substring(2, 4) + "/" +
                date.substring(4, 8);
        Date parsedDate = null;
        try {
            parsedDate = sdf.parse(formattedString);
            return parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
