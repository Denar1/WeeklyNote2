package com.denar.conteiners;

import com.denar.main.Main;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

public class EventItemListener implements ItemListener {

    Main main;

    public EventItemListener(Main m) {
        this.main = m;
    }

    public void itemStateChanged(ItemEvent ie) {
        if (ie.getSource() == main.sideMenu.dayCh) {
            if (main.calendar.numMonth[main.sideMenu.monthCh.getSelectedIndex()][main.sideMenu.dayCh.getSelectedIndex()].getBackground()
                    != main.calendar.df) {
                main.calendar.numMonth[main.sideMenu.monthCh.getSelectedIndex()][main.sideMenu.dayCh.getSelectedIndex()].setBackground(main.calendar.df);
            } else if (ie.getSource() == main.calendar.numMonth[main.calendar.currentMonth][main.calendar.currentNumDays]) {
                main.calendar.colorTodayDays();
            }

        }

    }
}
