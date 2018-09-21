package com.denar.main;


import com.denar.conteiners.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main extends Frame {


	public Panel panelTop, panelBottom;

	public WindowCreateNote2 windowCN2;
	public SideMenu sideMenu;
	public MyCalendar calendar;
	public Week week;
//	public Week2 week2;
	public EventItemListener eventIL;


	public Color backMainColor;


// Конструктор основного класса
	public Main(String title) {
		super(title);					// Заголовок главного окна



		backMainColor = new Color(0xB3B3B3);


		sideMenu = new SideMenu(this);
		windowCN2 = new WindowCreateNote2(this);

		calendar = new MyCalendar(this);	// класс календарь
		week = new Week(this);				// класс неделя
//		week2 = new Week2(this);
		eventIL = new EventItemListener(this);


		windowCN2.readFile();
		windowCN2.panelObj.setData(null, null, null,
				null, null, null, null);

		week.setCountObjInDayWeek(null);




		panelTop = new Panel();			// верхняя панель для меню и календаря
		panelBottom = new Panel();		// нижняя панель для недели



// Параметры верхней панели
		panelTop.setPreferredSize(new Dimension(800, 300));
		panelTop.setLayout(null);

// Параметры нижней панели
		panelBottom.setPreferredSize(new Dimension(800, 500));
		panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));


		panelTop.add(windowCN2.panelCreateNote);
		panelTop.add(sideMenu.panelSideMenu);					// добавление БМ и К
		panelTop.add(calendar.panelCalendar);					// в верхнию панель

		panelBottom.add(week.week);						// добавление Н в нижнию панель

// Параметры Главного Контейнера
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(panelTop);
		add(panelBottom);

		setBackground(backMainColor);
		setSize(800, 800);			// Размер Главного Контейнера
		setResizable(false);
		setLocationRelativeTo(null);

		addWindowListener(new MyWindowAdapter());

		setVisible(true);			// Сделать видимым окно Frame



	}





	// основной метод
	public static void main(String[] args) {

// Новый поток исполнения
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				// создание объекта Frame
				new Main("Weekly Note");

			}
		});

	}


}

// класс обрабатывающий события завершения программы на крестик во frame
class MyWindowAdapter extends WindowAdapter {

	public void windowClosing(WindowEvent we) {
		System.exit(0);
	}
}
