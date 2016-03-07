package com.yurikami.lib.util;

import com.yurikami.lib.entity.Datetime;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class DateUtils {
    private static long sLastTimestamp = 0l;
    private static Calendar sLastCalendar;

    public static Date now() { return new Date(); }
    public static long nowTimestamp() { return System.currentTimeMillis(); }


    /**
     *
     * 提取时间戳之中的年月日时分秒之一的数值
     * @param timestamp 时间戳
     * @param part "year" "month" "day" "hour" "minute" "second"
     * @return 年月日时分秒之一的数值
     */
    public static Integer extractTimestamp(long timestamp, String part){
        Calendar c;
        if(timestamp == sLastTimestamp && sLastCalendar != null) {
            c = sLastCalendar;
        }else {
            c = Calendar.getInstance();
            c.setTimeInMillis(timestamp);
            sLastCalendar = c;
        }
        if("year".equals(part)){ return c.get(Calendar.YEAR); }
        else if("month".equals(part)){ return c.get(Calendar.MONTH) + 1; }
        else if("day".equals(part)){ return c.get(Calendar.DAY_OF_MONTH); }
        else if("hour".equals(part)){ return c.get(Calendar.HOUR_OF_DAY); }
        else if("minute".equals(part)){ return c.get(Calendar.MINUTE); }
        else if("second".equals(part)){ return c.get(Calendar.SECOND); }
        return null;
    }
    public static Integer year(long timestamp){ return extractTimestamp(timestamp, "year"); }
    public static Integer month(long timestamp){ return extractTimestamp(timestamp, "month"); }
    public static Integer day(long timestamp){ return extractTimestamp(timestamp, "day"); }
    public static Integer hour(long timestamp){ return extractTimestamp(timestamp, "hour"); }
    public static Integer minute(long timestamp){ return extractTimestamp(timestamp, "minute");}
    public static Integer second(long timestamp){ return extractTimestamp(timestamp, "second"); }

    /**
     *
     * 根据年月日时分秒来生成unix时间戳
     * @return unix时间戳
     */
    public static long newTimestamp(int year, int month, int day,
                                     int hour, int minute, int second){
        Calendar c = Calendar.getInstance();
        c.set(year,month - 1,day,hour,minute,second);
        return c.getTimeInMillis();
    }
    public static long newDateTimestamp(int year, int month, int day){
        return newTimestamp(year, month, day, 0, 0, 0);
    }
    public static long newTimeTimestamp(int hour, int minute, int second){
        return newTimestamp(0,0,0,hour,minute,second);
    }

    /**
     * 计算某年中某月的天数,不填返回null
     * @param datetime year,month 必填
     * @return 某年中某月的天数
     */
    public static int dayInMonth(Datetime datetime){
        if(datetime == null || datetime.getYear() == null || datetime.getMonth() == null) {
            throw new IllegalArgumentException("Illegal datetime,without year or month");
        }
        Calendar c = Calendar.getInstance();
        c.set(datetime.getYear(), datetime.getMonth() - 1, 1);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算某年某月某日是星期几
     * @param datetime year,month,day 必填
     * @return 周一:1 周二:2 周三:3 ...
     */
    public static int dayOfWeek(Datetime datetime){
        if(datetime == null || datetime.getYear() == null || datetime.getMonth() == null
                || datetime.getDay() == null) {
            throw new IllegalArgumentException("Illegal datetime,without year or month or day");
        }
        Calendar c = Calendar.getInstance();
        c.set(datetime.getYear(), datetime.getMonth() - 1, datetime.getDay());
        int d = c.get(Calendar.DAY_OF_WEEK) - 1;
        return (d == 0 ? 7 : d);
    }
}
