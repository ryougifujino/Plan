package com.yurikami.lib.model;

import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.Utils;

import java.util.Calendar;

/**
 * Created by WINFIELD on 2016/3/6.
 */
public class Datetime {
    private Integer year;
    private Integer month;
    private Integer week;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Integer second;

    private String[] chnWeeks = {"一","二","三","四","五","六","日"};
    private String chnWeek;
    private Calendar c;

    public Datetime(){ }

    private Datetime(Integer year, Integer month, Integer week, Integer day, Integer hour, Integer minute, Integer second) {
        this.year = year;
        this.month = month;
        this.week = week;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static Datetime buildDatetime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second){
        return new Datetime(year, month,null, day, hour, minute, second);
    }
    public static Datetime buildDatetime(Integer year, Integer month,Integer week, Integer day, Integer hour, Integer minute, Integer second){
        return new Datetime(year, month,week, day, hour, minute, second);
    }

    public static Datetime buildDate(Integer year, Integer month, Integer day){
        return buildDatetime(year, month, day, null, null, null);
    }

    public static Datetime buildTime(Integer hour, Integer minute, Integer second){
        return buildDatetime(null, null, null, hour,minute,second);
    }

    public static Datetime buildTodayDate(){
        return DateUtils.dateOfToday();
    }


    public Integer getYear() {
        return year;
    }

    public Datetime setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getMonth() {
        return month;
    }

    public Datetime setMonth(Integer month) {
        this.month = month;
        return this;
    }

    public Integer getWeek() {
        return week;
    }

    public Datetime setWeek(Integer week) {
        this.chnWeek = (week >= 1 && week <= 7) ? chnWeeks[week - 1] : "";
        this.week = week;
        return this;
    }

    public Integer getDay() {
        return day;
    }

    public Datetime setDay(Integer day) {
        this.day = day;
        return this;
    }

    public Integer getHour() {
        return hour;
    }

    public Datetime setHour(Integer hour) {
        this.hour = hour;
        return this;
    }

    public Integer getMinute() {
        return minute;
    }

    public Datetime setMinute(Integer minute) {
        this.minute = minute;
        return this;
    }

    public Integer getSecond() {
        return second;
    }

    public Datetime setSecond(Integer second) {
        this.second = second;
        return this;
    }

    public String getChnWeek() {
        return chnWeek;
    }

    public Datetime setChnWeek(String chnWeek) {
        this.chnWeek = chnWeek;
        return this;
    }

    public boolean isSameWith(Datetime t){
        if(Utils.isAnyNull(t, t.getYear(), t.getMonth(), t.getDay(), year, month, day)){
            return false;
        }
        if (year.intValue() == t.getYear() && month.intValue() == t.getMonth() && day.intValue() == t.getDay()) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 把Datetime实例合法化，如果有null（不包括week）则作初始化
     * @return 合法的Datetime实例
     */
    public Datetime valid(){
        if (getYear() == null) setYear(0);
        if (getMonth() == null) setMonth(1);
        if (getDay() == null) setDay(1);
        if (getHour() == null) setHour(0);
        if (getMinute() == null) setMinute(0);
        if (getSecond() == null) setSecond(0);
        if (c == null) c = Calendar.getInstance();
        c.set(getYear(),getMonth() - 1,getDay(),getHour(),getMinute(),getSecond());
        setYear(c.get(Calendar.YEAR)).setMonth(c.get(Calendar.MONTH) + 1)
                .setDay(c.get(Calendar.DATE)).setHour(c.get(Calendar.HOUR))
                .setMinute(c.get(Calendar.MINUTE)).setSecond(c.get(Calendar.SECOND))
                .setWeek(DateUtils.formatWeek(c.get(Calendar.DAY_OF_WEEK)));
        return this;
    }
}
