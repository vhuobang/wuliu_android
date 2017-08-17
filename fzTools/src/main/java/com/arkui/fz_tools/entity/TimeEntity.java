package com.arkui.fz_tools.entity;

import java.util.List;

/**
 * Created by nmliz on 2017/6/30.
 */

public class TimeEntity {

    public TimeEntity(String monthName, String month, List<String> hourList, List<String> minList) {
        this.month = month;
        this.monthName = monthName;
        this.hourList = hourList;
        this.minList = minList;
    }

    String month;
    String monthName;

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    List<String> hourList;
    List<String> minList;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<String> getHourList() {
        return hourList;
    }

    public void setHourList(List<String> hourList) {
        this.hourList = hourList;
    }

    public List<String> getMinList() {
        return minList;
    }

    public void setMinList(List<String> minList) {
        this.minList = minList;
    }

    @Override
    public String toString() {
        return "TimeEntity{" +
                "month='" + month + '\'' +
                '}';
    }
}
