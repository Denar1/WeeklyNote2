package com.denar.Files;


import com.denar.main.Main;

import java.awt.*;
import java.io.Serializable;
import java.util.Calendar;


public class SaveData implements Serializable {

    Main main;

    public String object, name, month, time, day, year, note;

    public int NUMBER,
            INDEX_MONTH,
            TIME,
            COLS_WEEK,
            ROWS_WEEK,
            INDEX_IN_CALENDAR;

    public SaveData(Main main, String d, String m, String y, String o, String n, String t, String note) {
        this.main = main;
        this.day = d;
        this.month = m;
        this.year = y;
        this.object = o;
        this.name = n;
        this.time = t;
        this.note = note;

        init();
    }

    public void init() {
        this.NUMBER = Integer.parseInt(day);
        this.INDEX_MONTH = getIndexMonth();
        this.TIME = getIndexTime();
        this.COLS_WEEK = getColsWeek();
        this.ROWS_WEEK = getIndexTime();
        this.INDEX_IN_CALENDAR = getIndexDayInCalendar();
    }



// Возвращает индекс компонента в календаре
    private int getIndexDayInCalendar() {
        int i = 0;
        Component[] c = main.calendar.calMonthNubm[main.calendar.calendar.get(Calendar.MONTH)].getComponents();

        while (c[i].getBackground().equals(main.calendar.previousMonthColor)) {
            i++;
        }

        int n = Integer.parseInt(day) + i;

        return n;
    }

    private int getIndexMonth() {
        int flagMonth = 0;
        for(int i = 0; i < main.calendar.monthList.length; i++) {
            if(month.equals(main.sideMenu.nameMonth[i])) {
                flagMonth = i;
                break;
            }
        }
        return flagMonth;
    }

    private int getIndexTime() {
        int h = 0;
        switch (time) {
            case "7:00":
                h = 0;
                break;
            case "8:00":
                h = 1;
                break;
            case "9:00":
                h = 2;
                break;
            case "10:00":
                h = 3;
                break;
            case "11:00":
                h = 4;
                break;
            case "12:00":
                h = 5;
                break;
            case "13:00":
                h = 6;
                break;
            case "14:00":
                h = 7;
                break;
            case "15:00":
                h = 8;
                break;
            case "16:00":
                h = 9;
                break;
            case "17:00":
                h = 10;
                break;
            case "18:00":
                h = 11;
                break;
        }
        return h;
    }

    private int getColsWeek() {
        int j = 0;

        Component[] c = main.calendar.calMonthNubm[main.calendar.calendar.get(Calendar.MONTH)].getComponents();

        while(c[j].getBackground().equals(main.calendar.previousMonthColor)) {

            j++;

        }

        int k = (Integer.parseInt(day) + --j) % 7;

        return k;
    }


    public String toString() {
        return day + " " + month + " " + year + "\n" +
                object + "\n" +
                name + "\n" +
                time + "\n" +
                note;
    }

}
