/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Zubanov Dmitry aka javadimon, zubanov@gmail.com Omsk, Russia, created
 * 03.10.2010 (C) Copyright by Zubanov Dmitry
 */
package ru.glamour.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTime {

    public Date getDate() {
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance(Locale.getDefault());
        return calendar.getTime();
    }

    public long getTime() {
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance(Locale.getDefault());
        return calendar.getTimeInMillis();
    }

    public Calendar getCalendar() {
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance(Locale.getDefault());
        return calendar;
    }

    public static String getFormatedDate(String format, Date date) {
        String strDate = "undefined";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            strDate = sdf.format(date);
        } catch (Exception ex) {
            Logger.getLogger(DateTime.class.getName()).log(Level.FINE, format, ex);
        }

        return strDate;
    }

    public static String getFormatedDate(String format, Calendar calendar) {
        String strDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            strDate = sdf.format(calendar.getTime());
        } catch (Exception ex) {
            Logger.getLogger(DateTime.class.getName()).log(Level.SEVERE, format, ex);
        }

        return strDate;
    }

    public static Date getDateFromString(String format, String strDate) {
        Date result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            result = sdf.parse(strDate);
        } catch (ParseException ex) {
            Logger.getLogger(DateTime.class.getName()).log(Level.SEVERE,
                    "Format: " + format + ", date: " + strDate, ex);
        }

        return result;
    }

    public static Calendar getCalendarFromString(String format, String strDate) {
        Date result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            result = sdf.parse(strDate);
        } catch (ParseException ex) {
            Logger.getLogger(DateTime.class.getName()).log(Level.SEVERE, null, ex);
        }

        GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance(CurrentTimeZone.getDefault().getSimpleTimeZone());
        cal.setTime(result);
        return cal;
    }

    public long getTimeInterval(Date start, Date end, int returnType) {
        return getTimeIntervalBeetwenDates(start.getTime(), end.getTime(),
                returnType);
    }

    public long getTimeInterval(Calendar start, Calendar end, int returnType) {
        return getTimeIntervalBeetwenDates(start.getTimeInMillis(), end.getTimeInMillis(),
                returnType);
    }

    private long getTimeIntervalBeetwenDates(long start, long end, int returnType) {
        long value = -1;
        if (start > end) {
            return value;
        }

        long val = end - start;
        switch (returnType) {
            case 0:
                value = val;
                break;

            case 1:
                value = val / 1000;
                break;

            case 2:
                value = val / 1000l / 60l;
                break;

            case 3:
                value = val / 1000l / 60l / 60l;
                break;

            case 4:
                value = val / 1000l / 60l / 60l / 24l;
                break;

            default:
                value = -1;
        }

        return value;
    }

    public static Date getDateEndOfDay(Date date) {
        String s = getFormatedDate("dd.MM.yy", date);
        return getDateFromString("dd.MM.yy HH:mm:ss", s + " 23:59:59");
    }

    public static Date getDateBeginOfDay(Date date) {
        String s = getFormatedDate("dd.MM.yy", date);
        return getDateFromString("dd.MM.yy HH:mm:ss", s + " 00:00:01");
    }

    public static Date getDateAfterRoll(Date date, int field, int value) {
        GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        cal.add(field, value);
        return cal.getTime();
    }

    public static Calendar getCalendarAfterRoll(Date date, int field, int value) {
        GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        cal.add(field, value);
        return cal;
    }

    public Calendar getCalendarAfterRoll(Calendar cal, int field, int value) {
        cal.roll(field, value);
        return cal;
    }

    /**
     * 
     * @param date
     * @param hour
     * @param min
     * @param second
     * @param ms
     * @return Date or null
     */
    public static Date getDate(Date date, String hour, String min, String second, String ms) {
        try {
            GregorianCalendar calendar = (GregorianCalendar) java.util.Calendar.getInstance(Locale.getDefault());
            calendar.setTime(date);
            calendar.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(hour));
            calendar.set(GregorianCalendar.MINUTE, Integer.parseInt(min));
            calendar.set(GregorianCalendar.SECOND, Integer.parseInt(second));
            calendar.set(GregorianCalendar.MILLISECOND, Integer.parseInt(ms));
            return calendar.getTime();
            
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, null, e);
            return null;
        }

    }
}
