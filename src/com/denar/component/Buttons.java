package com.denar.component;

import com.denar.conteiners.MyCalendar;
import com.denar.main.Main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.Calendar;

public class Buttons extends MouseAdapter {

	Main main;
	MyCalendar myCalendar;
	Calendar calendar;
	public Button save, change, routeLeft, routeRight;


	public Buttons(Main main, MyCalendar myCalendar) {
		this.main = main;
		this.myCalendar = myCalendar;
		calendar = Calendar.getInstance();


		save = new Button("Сохранить");
		change = new Button("Изменить");
		routeLeft = new Button("<");
		routeRight = new Button(">");

		save.setPreferredSize(new Dimension(70, 25));
		save.setBackground(new Color(0xcccccc));

		change.setPreferredSize(new Dimension(70, 25));
		change.setBackground(new Color(0xcccccc));

		routeLeft.setPreferredSize(new Dimension(15, 15));

		routeRight.setPreferredSize(new Dimension(15, 15));
	}

//	public void mouseClicked(MouseEvent me) {
//		if(calendar.get(Calendar.MONTH) != myCalendar.monthList.length -1) {
//			myCalendar.month = myCalendar.monthList[];
//		}
//	}



}
