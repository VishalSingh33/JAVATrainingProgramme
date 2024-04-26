package com.notification.service.b2bProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    // Convert Date to String
    public static String dateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    // Convert String to Date
    public static Date stringToDate(String dateString, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateString);
    }

    // Add days to a Date
    public static Date addDaysToDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    // Subtract days from a Date
    public static Date subtractDaysFromDate(Date date, int days) {
        return addDaysToDate(date, -days);
    }

    // Get the current Date
    public static Date getCurrentDate() {
        return new Date();
    }
}
