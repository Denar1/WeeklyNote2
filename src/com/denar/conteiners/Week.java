package com.denar.conteiners;


import com.denar.Files.SaveData;
import com.denar.main.Main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class Week extends MouseAdapter {

	public Panel week;
	public Panel pwWest, pwNorth, pwCenter, pwcNorth, pwcCenter, pwnTime, pwnDays;
	public Panel[] listPanelDays;
	public Label time, emptyLabTime;
	public Label[][] listLabelDays;
	public Label[] listLabelTime, listLabelNumDaysWeek;

	Dimension dimLab;
	String[] nameDays = {"Понедельник", "Вторник", "Среда", "Четверг",
			"Пятница", "Суббота", "Воскресенье"};

	Main main;

	Color eee = new Color(0xeeeeee);
	Color fc = new Color(0xfcfcfc);



	// Конструктор Недели
	public Week(Main main) {

		this.main = main;

		dimLab = new Dimension(100, 30);		// размер для дней недели


		week = new Panel();						// создание панели для недели

		listPanelDays = new Panel[7];
		pwWest = new Panel();
		pwNorth = new Panel();
		pwCenter = new Panel();
		pwcNorth = new Panel();
		pwcCenter = new Panel();
		pwnTime = new Panel();
		pwnDays = new Panel();

		listLabelDays = new Label[7][12];
		listLabelTime = new Label[12];
		listLabelNumDaysWeek = new Label[7];

//		week.addMouseListener(this);

		time = new Label("Время");
		time.setPreferredSize(new Dimension(50, 32));
		time.setAlignment(Label.CENTER);

		emptyLabTime = new Label();
		emptyLabTime.setPreferredSize(new Dimension(50, 20));


// Параметры Н
		week.setPreferredSize(new Dimension(800, 500));
		week.setLayout(new BorderLayout());

// Параметры Северной панели
		pwNorth.setPreferredSize(new Dimension(800, 40));
		pwNorth.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		pwNorth.setBackground(new Color(0x9FD8E8));


		pwnTime.setPreferredSize(new Dimension(50, 40));
//		pwnTime.setBackground(Color.gray);

		pwnDays.setPreferredSize(new Dimension(750, 40));
		pwnDays.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 5));

//		pwnDays.setBackground(Color.cyan);

// Параметры Западной панели
		pwWest.setPreferredSize(new Dimension(50, 460));
		pwWest.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));



// Параметры Центральной панели
		pwCenter.setPreferredSize(new Dimension(750, 460));
		pwCenter.setLayout(new BorderLayout());


// Параметры Северной панели обертки в центральной панели
		pwcNorth.setPreferredSize(new Dimension(700, 20));
		pwcNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));


// Параметры Центральной панели обертки в центральной панели
		pwcCenter.setPreferredSize(new Dimension(700, 400));
		pwcCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));



// Внутренний класс для меток Северной панели
		class PwNorthLabel extends Label {
			public PwNorthLabel(String str, int i) {
				super(str, i);
				super.setPreferredSize(new Dimension(100, 32));
//				super.setBackground(Color.darkGray);
			}
		}


// Внутренний класс для меток Западной панели
		class PwWestLabel extends Label {
			public PwWestLabel(String str, int i) {
				super(str, i);
				super.setPreferredSize(new Dimension(50, 30));
				super.setBackground(new Color(0x9CD4F0));
			}
		}




// Внутренний класс для меток Центральной панели
//		class PwCenterLabel extends Label {
//			public PwCenterLabel(String str, int i) {
//				super(str, i);
//				super.setPreferredSize(new Dimension(100, 32));
//				super.setBackground(new Color(0xeeeeee));
//			}
//
//
//		}


//		class panelDaysW extends Panel {
//			public panelDaysW() {
//				super.setPreferredSize(new Dimension(100, 500));
//				super.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 2));
////				super.setBackground(Color.yellow);
//			}
//		}

// Добавление Лейблов дней недели и Лейбла Время в панель для
// северной панели
		pwnTime.add(time);
		for(int i = 0; i < nameDays.length; i++) {
			pwnDays.add(new PwNorthLabel(nameDays[i], FlowLayout.CENTER));
		}

		pwNorth.add(pwnTime);
		pwNorth.add(pwnDays);


		pwCenter.add(pwcNorth, BorderLayout.NORTH);
		pwCenter.add(pwcCenter, BorderLayout.CENTER);


		int flag = 7;
// Добавление меток в Западную панель
		pwWest.add(emptyLabTime);
		for(int i = 0; i < listLabelTime.length; i++) {
			listLabelTime[i] = new PwWestLabel(flag + ":00", FlowLayout.CENTER);
			pwWest.add(listLabelTime[i]);
			flag++;
		}

// Добавление списка меток чисел дней недели
		int firstDayWeek = getNumberFirstDayMyWeek(null);

		for(int i = 0; i < listLabelNumDaysWeek.length; i++) {
			listLabelNumDaysWeek[i] = new Label(firstDayWeek  + "");
			listLabelNumDaysWeek[i].setPreferredSize(new Dimension(100, 20));
			listLabelNumDaysWeek[i].setBackground(eee);
			listLabelNumDaysWeek[i].setAlignment(Label.CENTER);

			if(firstDayWeek < main.calendar.calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
				firstDayWeek++;
			else
				firstDayWeek = 1;

			pwcNorth.add(listLabelNumDaysWeek[i]);
		}


// Добавление меток в Центральную панель
// Добавление списка меток чисел дней недели
//		appendListLabelInWeek(null);


		for(int i = 0; i < listPanelDays.length; i++) {
			listPanelDays[i] = new Panel();
			listPanelDays[i].setPreferredSize(new Dimension(100, 400));
			listPanelDays[i].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 2));

			for(int k = 0; k < listLabelDays[i].length; k++) {

				listLabelDays[i][k] = new Label();

				listLabelDays[i][k].setPreferredSize(new Dimension(100, 30));
				listLabelDays[i][k].setBackground(fc);
				listLabelDays[i][k].addMouseListener(this);
				listPanelDays[i].add(listLabelDays[i][k]);


			}


			pwcCenter.add(listPanelDays[i]);
//			pwCenter.add(listPanelDays[i]);
		}


		week.setPreferredSize(new Dimension(800, 500));
		week.add(pwWest, BorderLayout.WEST);
		week.add(pwNorth, BorderLayout.NORTH);
		week.add(pwCenter, BorderLayout.CENTER);

		colorTodayDayWeek();




// Конец Конструктора
	}



// Добавление списка меток чисел дней недели
	public void appendListLabelInWeek(String d) {
		int firstDayWeek = getNumberFirstDayMyWeek(d);

		for(int i = 0; i < listLabelNumDaysWeek.length; i++) {
			listLabelNumDaysWeek[i] = new Label(firstDayWeek  + "");
			listLabelNumDaysWeek[i].setPreferredSize(new Dimension(100, 20));
			listLabelNumDaysWeek[i].setBackground(eee);
			listLabelNumDaysWeek[i].setAlignment(Label.CENTER);

			if(firstDayWeek < main.calendar.calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
				firstDayWeek++;
			else
				firstDayWeek = 1;

			pwcNorth.add(listLabelNumDaysWeek[i]);
		}
	}




// Возвращает номер дня недели
	public int getWeekDay() {
		int d = main.calendar.calendar.get(Calendar.DAY_OF_WEEK);
		--d;
		if ( d == 0 )
			d = 7;
		return d;
	}


// Подсветка сегоднешнего дня недели в панели недели
	private void colorTodayDayWeek() {
		int indexDayWeek = getWeekDay();

//		listPanelDays[--indexDayWeek].setBackground(main.calendar.currentD);
		listLabelNumDaysWeek[--indexDayWeek].setBackground(main.calendar.currentD);
	}


// Расстановка чисел месяца в недели
	public void setSelectedDay(int indexMonth, int indexNum) {


	}

// Возвращает число первого дня недели --(число месяца)
	public int getNumberFirstDayMyWeek(String day) {
		int i = 0;

		if(day == null) {
			Component[] c = main.calendar.calMonthNubm[main.calendar.calendar.get(Calendar.MONTH)].getComponents();

			while (c[i].getBackground().equals(main.calendar.previousMonthColor)) {
				i++;
			}

			int n = (main.calendar.calendar.get(Calendar.DAY_OF_MONTH) + i) - 1;
			Label z = (Label) c[n];
			if(n <= 6)
				return Integer.parseInt(z.getText());
			else {
				n = ((n / 7) * 7);
				z = (Label) c[n];
				return Integer.parseInt(z.getText());
			}
		}
		else {
			Component[] c = main.calendar.calMonthNubm[main.calendar.calendar.get(Calendar.MONTH)].getComponents();

			while (c[i].getBackground().equals(main.calendar.previousMonthColor)) {
				i++;
			}

			int n = (Integer.parseInt(day) + i) - 1;
			Label z = (Label) c[n];
			if(n <= 6)
				return Integer.parseInt(z.getText());
			else {
				n = ((n / 7) * 7);
				z = (Label) c[n];
				return Integer.parseInt(z.getText());
			}
		}
	}


// Возвращает index дня недели указанного в аргументе
	public int getIndexDayWeek(int d, int month) {
		int i = 0;

//		Component[] c = main.calendar.calMonthNubm[main.calendar.calendar.get(Calendar.MONTH)].getComponents();
		Component[] c = main.calendar.calMonthNubm[month].getComponents();
		while(c[i].getBackground().equals(main.calendar.previousMonthColor)) {

			i++;

		}

		int k = (d + --i);

		if(k < 7) {
			return k;
		}
		else {
			k %= 7;

			return k;
		}


	}


// Установка заметки в днях недели по часам
//	public void setColorInWeekNote(int month, int day, String time) {
//		int indexDayWeek = getIndexDayWeek(day, month);
//		int indexFirstDayWeek = getNumberFirstDayMyWeek(null);
//		int h = 0;
//		int cd = main.calendar.currentNumDays;
//		int nextMonth = main.calendar.currentMonth + 1;
//		int numbNextMonth = 1;
//		int numbLastDayWeek = Integer.parseInt(
//				listLabelNumDaysWeek[listLabelNumDaysWeek.length -1].getText()
//		);
////		int indexNumbNextMonth = getIndexDayWeek(numbNextMonth, nextMonth);
//
//
//
//
//		switch(time) {
//			case "7:00":
//				h = 0;
//				break;
//			case "8:00":
//				h = 1;
//				break;
//			case "9:00":
//				h = 2;
//				break;
//			case "10:00":
//				h = 3;
//				break;
//			case "11:00":
//				h = 4;
//				break;
//			case "12:00":
//				h = 5;
//				break;
//			case "13:00":
//				h = 6;
//				break;
//			case "14:00":
//				h = 7;
//				break;
//			case "15:00":
//				h = 8;
//				break;
//			case "16:00":
//				h = 9;
//				break;
//			case "17:00":
//				h = 10;
//				break;
//			case "18:00":
//				h = 11;
//				break;
//		}
//
//
//		if(day >= indexFirstDayWeek &&
//				day < (indexFirstDayWeek + 7)
//				&& month == main.calendar.currentMonth) {
//
//
//			if(day < cd && month == main.calendar.currentMonth) {
////				listLabelDays[indexDayWeek][h].setBackground(main.calendar.previousDays);
//				listLabelDays[indexDayWeek][h].setBackground(main.calendar.previousDays);
//				listLabelDays[indexDayWeek][h].setText(incrementCountObj(indexDayWeek, h) + "");
//			}
//			else if(day >= main.calendar.currentNumDays){
////				listLabelDays[indexDayWeek][h].setBackground(main.calendar.df);
//				listLabelDays[indexDayWeek][h].setBackground(main.calendar.df);
//				listLabelDays[indexDayWeek][h].setText(incrementCountObj(indexDayWeek, h) + "");
//			}
////			else if(day < cd && month == (main.calendar.currentMonth + 1)) {
////				listLabelDays[getWeekDay(main.calendar.date, day)][h].setBackground(main.calendar.df);
////
////			}
//		}
//		else if(day >= numbNextMonth && day <= numbLastDayWeek &&
//				month == nextMonth) {
//			listLabelDays[indexDayWeek][h].setBackground(main.calendar.df);
//			listLabelDays[indexDayWeek][h].setText(incrementCountObj(indexDayWeek, h) + "");
//		}
//
//	}


// Метод не работает если переключатся на другой месяц. Доделать.


// Устанавливает количество объектов на одно и тоже время
	public void setCountObjInDayWeek(String d) {
		int countObj2 = 1;
		int indexDayWeek = 0;
		int h = 0;
		int j = 1;
		int i = 1;

		int nextMonth = main.calendar.currentMonth + 1;
		int numbNextMonth = 1;
		int numbLastDayWeek = Integer.parseInt(
				listLabelNumDaysWeek[listLabelNumDaysWeek.length -1].getText()
		);
//		int flagDay = 0;
//		listLabelNumDaysWeek[listLabelNumDaysWeek.length -1].getText();

// Проходим в цикле по объеткам
		for(SaveData sd : main.windowCN2.listSaveObj) {

//			flagDay = sd.NUMBER;

// Если число объекта попадает в диапазон чисел недели то запускается код
			if((sd.NUMBER >= getNumberFirstDayMyWeek(d) &&
					sd.NUMBER < (getNumberFirstDayMyWeek(d) + 7)
//					&& sd.month.equals(main.calendar.monthList[main.calendar.calendar.get(Calendar.MONTH)])) {
					&& sd.INDEX_MONTH == main.calendar.currentMonth) ||
					(sd.NUMBER >= numbNextMonth && sd.NUMBER <= numbLastDayWeek &&
							sd.INDEX_MONTH == nextMonth)) {

				indexDayWeek = getIndexDayWeek(sd.NUMBER, sd.INDEX_MONTH);

// Получаем индекс Label времени
				switch (sd.time) {
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


// Зпаускаем 2 цикл в котором сравниваем выбранный объект sd из первого цикла
// с остальными объетками
				while (i < main.windowCN2.listSaveObj.size()) {


// Условие: Если объект sd совпадает по дню, месяцу и времени с другим
// увеличиваем переменную количества объектов
					if (sd.day.equals(main.windowCN2.listSaveObj.get(i).day) &&
							sd.month.equals(main.windowCN2.listSaveObj.get(i).month) &&
							sd.time.equals(main.windowCN2.listSaveObj.get(i).time)) {

						if(!sd.equals(main.windowCN2.listSaveObj.get(i)))
							++countObj2;

					}

					i++;

				}


// Условие: Если у Label не установлено количество объетов то устанавливаем
				if(listLabelDays[indexDayWeek][h].getText().equals("") ||
						listLabelDays[indexDayWeek][h].getText().equals(null)) {
					listLabelDays[indexDayWeek][h].setText(countObj2 + "");
					if(Integer.parseInt(listLabelNumDaysWeek[indexDayWeek].getText()) <
							(main.calendar.currentNumDays + 1)) {
						listLabelDays[indexDayWeek][h].setBackground(main.calendar.previousDays);
					}
					else {
						listLabelDays[indexDayWeek][h].setBackground(main.calendar.df);
					}
				}



				++j;
				i = j;

				countObj2 = 1;			// Устанавливаем изначальное значение

			}
		}
	}

// Увеличивает число объектов установленных на одно и тоже время
	public int incrementCountObj(int idw, int t) {
		String co = listLabelDays[idw][t].getText();
		int ico = 1;
		if(co == "" || co == null) {
			return ico;
		}
		else {
			ico = Integer.parseInt(co);
			return ++ico;
		}
	}

// Показ объекта при нажатии на дне недели
	public void showNote(String d, String m, String t) {
		if (d != null && m != null) {
			for (SaveData sd : main.windowCN2.listSaveObj) {
				if (sd.day.equals(d) && sd.month.equals(m) &&
						sd.time.equals(t)) {
					main.windowCN2.panelObj.setData(sd.day, sd.month, sd.year, sd.object,
							sd.name, sd.time, sd.note);
				}
			}
		}
//		else {
//
//			String curND = (main.calendar.currentNumDays + 1) + "";
//
//			for (SaveData sd : main.windowCN2.listSaveObj) {
//				if (sd.day.equals(curND) &&
//						sd.month.equals(main.calendar.monthList[main.calendar.currentMonth] + "")) {
//					main.windowCN2.panelObj.setData(sd.day, sd.month, sd.year, sd.object,
//							sd.name, sd.time, sd.note);
//				}
//				else {
//					main.windowCN2.panelObj.setData(null, null, null,
//							null, null, null, null);
//				}
//			}
//		}
	}




// Обработка событий мыши при нажатии на Label недели
	public Label eL;

	public void mousePressed(MouseEvent e) {

		eL = (Label) e.getSource();

		if(eL.getBackground() != fc) {
		found:
			for(int i = 0; i < listPanelDays.length; i++) {
				for(int j = 0; j < listLabelDays[i].length; j++) {
					if(eL == listLabelDays[i][j]) {
						String d = listLabelNumDaysWeek[i].getText();
						String m = main.calendar.month.getText();
						String t = listLabelTime[j].getText();


// Если первый день недели больше дня недели выбранного Label
// тогда показать предыдущий месяц
						if(getNumberFirstDayMyWeek(null) > Integer.parseInt(d)) {
							showNote(d, main.calendar.monthList[(main.calendar.currentMonth + 1)], t);
							break found;
						}
						else {
							showNote(d, m, t);
							break found;
						}
					}
				}
			}
		}


//		for(SaveData sd : main.windowCN2.listSaveObj) {
//			if(sourse.getText().equals(sd.day) && month.getText().equals(sd.month)) {
//				main.windowCN2.panelLeftCNTop.setData(sd.day, sd.month, sd.year, sd.object,
//						sd.name, sd.time, sd.note);
//			}
//			else if(sourse.getText().equals(sd.day) && month.getText().equals(sd.month)) {
//				main.windowCN2.panelLeftCNTop.setData(sd.day, sd.month, sd.year, sd.object,
//						sd.name, sd.time, sd.note);
//			}
//			else if(sourse.getText().equals(sd.day) && month.getText().equals(sd.month)) {
//				main.windowCN2.panelLeftCNTop.setData(sd.day, sd.month, sd.year, sd.object,
//						sd.name, sd.time, sd.note);
//			}
//
//
//		}

//		eL = (Label) e.getSource();
//
//
//		for(int i = 0; i < listPanelDays.length; i++) {
//			for(int j = 0; j < listLabelDays[i].length; j++) {
//				if(eL == listLabelDays[i][j] && eL.getBackground() == eee) {
//					sm.timeList.select(j);
//					listLabelDays[i][j].setBackground(df);
//				}
//				else {
//					listLabelDays[i][j].setBackground(eee);
//				}
//			}
//		}


//		if(sourse.getBackground() != Color.cyan) {
//			sourse.setBackground(Color.cyan);
//		}
//		else
//			sourse.setBackground(eee);


	}


//	Конец Класса
}
