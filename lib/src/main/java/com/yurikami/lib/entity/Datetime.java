package com.yurikami.lib.entity;

import com.yurikami.lib.util.DateUtils;

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

    public Datetime(){ }

    private Datetime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static Datetime buildDatetime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second){
        return new Datetime(year, month, day, hour, minute, second);
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

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.chnWeek = (week >= 1 && week <= 7) ? chnWeeks[week - 1] : "";
        this.week = week;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public String getChnWeek() {
        return chnWeek;
    }

    public void setChnWeek(String chnWeek) {
        this.chnWeek = chnWeek;
    }
}
