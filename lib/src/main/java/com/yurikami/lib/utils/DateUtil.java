package com.yurikami.lib.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class DateUtil {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static long mLastTimestamp = 0l;
    private static Calendar mLastCalendar;

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
        if(timestamp == mLastTimestamp && mLastCalendar != null) {
            c = mLastCalendar;
        }else {
            c = Calendar.getInstance();
            c.setTimeInMillis(timestamp);
            mLastCalendar = c;
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
        return newTimestamp(year,month,day,0,0,0);
    }
    public static long newTimeTimestamp(int hour, int minute, int second){
        return newTimestamp(0,0,0,hour,minute,second);
    }
}
