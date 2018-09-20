package com.denar.conteiners;



import com.denar.main.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

public class MyCalendar extends MouseAdapter implements ActionListener {



	Main main;


	public Panel panelCalendar, cal, calM, calDaysWeek, calNumb, wrapperCalMonthNumb;
	public Panel[] calMonthNubm;
	public Panel routeLeftPane, routeRightPane, monthPane;		// Панели для Левой, Правой кнопок. Панель обертка для Панелей Лейблов Месяцев
	public Panel wrapperMonthName;			// Массив Панелей для Меток Месяцев

	public Label month;
	public Label[][] numMonth = new Label[12][];

	public String[] monthList = {"Январь", "Февраль", "Март", "Апрель",
			"Май", "Июнь", "Июль", "Август",
			"Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
	public String[] daysWeek = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};


	public Button routeLeft, routeRight;
	public Button[] bList = new Button[2];


	public Color numMonthBackground;
	public Dimension numMonthSizeLabel;


	public Color ccc = new Color(0xcccccc);
	public Color previousDays = new Color(0xF35951);
	public Color df = new Color(0xAAFF75);
	public Color currentD = new Color(0x9CD4F0);
	public Color previousMonthColor = new Color(0xE7E7E7);

	public Calendar calendar = Calendar.getInstance();


	public Date date = new Date();


	public int currentMonth = calendar.get(Calendar.MONTH);
	public int currentNumDays = calendar.get(Calendar.DAY_OF_MONTH) - 1;


	class LabelCurrentMonth extends Label {
		public LabelCurrentMonth(String str, int i) {
			super(str, i);
			super.setPreferredSize(new Dimension(20, 20));
			super.setBackground(ccc);
		}

	}

	class LabelCurrentMonth2 extends Label {
		public LabelCurrentMonth2(String str) {
			super(str);
			super.setPreferredSize(new Dimension(100, 30));
			super.setAlignment(Label.CENTER);
//			super.setBackground(ccc);
		}

	}

	class labelPreviousMonth extends Label {
		public labelPreviousMonth() {
			super.setPreferredSize(new Dimension(20, 20));
			super.setBackground(new Color(0xE7E7E7));
		}

	}


	class LabelDaysWeek extends Label {
		public LabelDaysWeek(String str, int i) {
			super(str, i);
			super.setPreferredSize(new Dimension(20, 20));
//			super.setBackground(new Color(0xcccccc));
		}

	}





	// Конструктор Календаря (К)
	public MyCalendar(Main main) {

		calendar.setTime(date);

		this.main = main;


		numMonth[0] = new Label[Months.January.getCountDays()];
		numMonth[1] = new Label[Months.Fibruary.getCountDays()];
		numMonth[2] = new Label[Months.March.getCountDays()];
		numMonth[3] = new Label[Months.April.getCountDays()];
		numMonth[4] = new Label[Months.May.getCountDays()];
		numMonth[5] = new Label[Months.June.getCountDays()];
		numMonth[6] = new Label[Months.July.getCountDays()];
		numMonth[7] = new Label[Months.August.getCountDays()];
		numMonth[8] = new Label[Months.September.getCountDays()];
		numMonth[9] = new Label[Months.October.getCountDays()];
		numMonth[10] = new Label[Months.November.getCountDays()];
		numMonth[11] = new Label[Months.December.getCountDays()];





		numMonthBackground = new Color(0xE7E7E7);
		numMonthSizeLabel = new Dimension(20, 20);


		calMonthNubm = new Panel[12];
		wrapperMonthName = new Panel();

		routeLeftPane = new Panel();
		routeRightPane = new Panel();
		monthPane = new Panel();


		panelCalendar = new Panel();
		calDaysWeek = new Panel();        // панель для дней недели
		calNumb = new Panel();            // панель для чисел месяца
		wrapperCalMonthNumb = new Panel();
		calM = new Panel();                // панель для названия месяца
		cal = new Panel();                // главная панель для дней и чисел


// Параметры панели К
		panelCalendar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panelCalendar.setBackground(new Color(0xfcfcfc));
		panelCalendar.setBounds(500, 0, 299, 298);
		panelCalendar.addMouseListener(this);


// Размер и растоновка основной панели
		cal.setLayout(new FlowLayout(FlowLayout.CENTER));
//		cal.setLayout(null);
		cal.setPreferredSize(new Dimension(299, 299));
//		cal.setBackground(Color.pink);

// Размер и растоновка панели дней недели
		calDaysWeek.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 5));
		calDaysWeek.setBackground(new Color(0x9FD8E8));
		calDaysWeek.setPreferredSize(new Dimension(200, 30));


// Размер и растоновка панели месяца
		calM.setPreferredSize(new Dimension(200, 30));
		calM.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));    // Менеджер компоновки


// Размер и растоновка внешней панели чисел месяца
//		calNumb.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
		calNumb.setLayout(null);
		calNumb.setPreferredSize(new Dimension(200, 150));
//		calNumb.setBackground(Color.gray);

		Dimension sizeCalNumb = calNumb.getPreferredSize();					// Размер внешней панели чисел месяца

// Размер и расстановка обертки 12 панелей месяцев
		wrapperCalMonthNumb.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
//		wrapperCalMonthNumb.setPreferredSize(new Dimension(sizeCalNumb.width * 12 + 24, sizeCalNumb.height));
//		wrapperCalMonthNumb.setBackground(Color.cyan);



// Создание, размер и расстановка панелей чисел месяцев + добавление их в панель обертку
		for(int i = 0; i < 12; i++) {
			calMonthNubm[i] = new Panel();
			calMonthNubm[i].setLayout(new GridLayout(6, 6, 2, 2));
			calMonthNubm[i].setPreferredSize(new Dimension(sizeCalNumb.width, sizeCalNumb.height));
//			calMonthNubm[i].setBackground(Color.green);
//			calMonthNubm[i].add(new Label(i + ""));
			wrapperCalMonthNumb.add(calMonthNubm[i]);
		}





// Размер и расстановка пенелей названия месяцев + добавление их в панель обертку
		wrapperMonthName.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		for(int i = 0; i < 12; i++)
			wrapperMonthName.add(new LabelCurrentMonth2(monthList[i]));

//		monthPane.add(wrapperMonthName);


// Панели для кнопок
		routeLeftPane.setPreferredSize(new Dimension(30, 30));
		routeLeftPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 7));

		routeRightPane.setPreferredSize(new Dimension(30, 30));
		routeRightPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 7));

// Панель обератка для массива панелей месяцев
		monthPane.setPreferredSize(new Dimension(100, 30));
//		monthPane.setBackground(Color.yellow);
//		monthPane.setLayout(null);
//		createMonthLabel();


// Размер метки месяца
		month = new Label( monthList[currentMonth]);        // создание объекта метки
        month.setPreferredSize(new Dimension(70, 20));
		month.setAlignment(Label.CENTER);




// Кнопки стрелок, размер и добавление слушателя событий

		// Кнопка Лево
		routeLeft = new Button("<");
		routeLeft.setPreferredSize(new Dimension(15, 15));
		routeLeft.addActionListener(this);



		// Кнопка Право
		routeRight = new Button(">");
		routeRight.setPreferredSize(new Dimension(15, 15));
		routeRight.addActionListener(this);



// Добавление дней недели в панель
		for (int i = 0; i < daysWeek.length; i++)
			calDaysWeek.add(new LabelDaysWeek(daysWeek[i], FlowLayout.CENTER));


// Добавление месяца и кнопок стрелок
		bList[0] = (Button) routeLeftPane.add(routeLeft);            // добавление кнопки стрелка налево
		monthPane.add(month);                // добавление метки месяца в панель
		bList[1] = (Button) routeRightPane.add(routeRight);           // добавление кнопки стрелка направо


// Добавление чисел месяца
		createNumMonth();

		insertNumMonth2();

		currentPositionWrapperMonth();


// Подсветка сегоднешнего числа
//		calNumb.getComponent(calendar.get(Calendar.DAY_OF_MONTH)).setBackground(new Color(0x9CD4F0));




// Добавление элементов в основную панель
		calM.add(routeLeftPane);
		calM.add(monthPane);
		calM.add(routeRightPane);


		calNumb.add(wrapperCalMonthNumb);

		cal.add(calM);
		cal.add(calDaysWeek);
		cal.add(calNumb);

//		for(int i = 0; i < calMonthNubm.length; i++)
//			cal.add(calMonthNubm[i]);

		panelCalendar.add(cal);


		colorTodayDays();




// Конец Конструктора
	}


	public int dayOfMonth(int m) {
		if(m == 4 || m == 6 || m == 9 || m == 11)
			return 30;
		else if(m == 2)
			return 28;
		else
			return 31;
	}




// Обработка событий от кнопок


	// Метод подсветки сегоднешнего числа
	public void colorTodayDays() {
		numMonth[currentMonth][currentNumDays].setBackground(currentD);
	}


// Установка Background Лейбла при Чтении файла
	public void setBackroundColorLabelRF(int fm, int fd, String time) {

//		main.week.setColorInWeekNote(fm, fd, time);

		if(numMonth[fm][--fd].getBackground() != currentD) {
			if(fd > currentNumDays && fm >= currentMonth)
				numMonth[fm][fd].setBackground(df);
			else if(fd < currentNumDays && fm > currentMonth)
				numMonth[fm][fd].setBackground(df);
			else
				numMonth[fm][fd].setBackground(previousDays);
		}


	}


// Текущая позиция панели обертки месяцев

	public int posX = 200 * currentMonth;
//	public int posXMonth = 100 * currentMonth;

	public void currentPositionWrapperMonth() {

		wrapperCalMonthNumb.setBounds(-posX, 0, 2400, 150);
//		wrapperMonthName.setBounds(-posXMonth, 0, (monthPane.getPreferredSize().width * 12), 30);


	}



// Обработка событий при нажатии на кнопки лево или право
	int flag = currentMonth;
	int shift = 100;

	@Override
	public void actionPerformed(ActionEvent e) {

// Блок кода для переключения месяца
//		{
//			if (e.getSource() == bList[0]) {
//				if (month.getText() != "Январь") {
//					currentMonth--;
//					month.setText(monthList[currentMonth]);
//
//					calNumb.removeAll();
//					createNumMonth();
//					insertNumMonth();
//
//					if(currentMonth == calendar.get(Calendar.MONTH))
//						colorTodayDays();
//
//					calNumb.validate();
//				}
//			} else if (e.getSource() == bList[1]) {
//				if (month.getText() != "Декабрь") {
//					currentMonth++;
//					month.setText(monthList[currentMonth]);
//
//					calNumb.removeAll();
//					createNumMonth();
//					insertNumMonth();
//
//					if(currentMonth == calendar.get(Calendar.MONTH))
//						colorTodayDays();
//
//					calNumb.validate();
//				}
//			}
//
////			if (month.getText() == monthList[calendar.get(Calendar.MONTH)])
////				calNumb.getComponent(calendar.get(Calendar.DAY_OF_MONTH)).setBackground(new Color(0x9CD4F0));
//
//
//
//
//		}    // Конец блока




//		 Блок кода для переключения месяца 2
		{
			if (e.getSource() == bList[0]) {
				if (wrapperCalMonthNumb.getX() != 0) {

					flag--;

					month.setText(monthList[flag]);

					for(int i = 0; i < 2; i++) {
						wrapperCalMonthNumb.setLocation(wrapperCalMonthNumb.getX() + shift, wrapperCalMonthNumb.getY());
//						wrapperMonthName.setLocation(wrapperMonthName.getX() + (shift / 2), wrapperMonthName.getY());

						try {
							Thread.sleep(80);
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
					}



//					wrapperCalMonthNumb.setLocation(wrapperCalMonthNumb.getX() + calMonthNubm[0].getWidth(), wrapperCalMonthNumb.getY());


					if(currentMonth == calendar.get(Calendar.MONTH))
						colorTodayDays();

				}
			}
			else if (e.getSource() == bList[1]) {
				if (wrapperCalMonthNumb.getX() != -2200) {

					flag++;

					month.setText(monthList[flag]);

					for(int i = 0; i < 2; i++) {
						wrapperCalMonthNumb.setLocation(wrapperCalMonthNumb.getX() - shift, wrapperCalMonthNumb.getY());
//						wrapperMonthName.setLocation(wrapperMonthName.getX() - (shift / 2), wrapperMonthName.getY());

						try {
							Thread.sleep(80);
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
					}

					if(currentMonth == calendar.get(Calendar.MONTH))
						colorTodayDays();



				}
			}

//			if (month.getText() == monthList[calendar.get(Calendar.MONTH)])
//				calNumb.getComponent(calendar.get(Calendar.DAY_OF_MONTH)).setBackground(new Color(0x9CD4F0));




		}    // Конец блока


// Конец метода actionPerformed
	}



// Обработка событий при нажатии на Label день календаря
	@Override
	public void mousePressed(MouseEvent e) {

		Label sourse = (Label) e.getSource();
		int firstDayWeek = main.week.getNumberFirstDayMyWeek(sourse.getText());

		Component[] c = calMonthNubm[calendar.get(Calendar.MONTH)].getComponents();
		int m = 0;
		while (c[m].getBackground().equals(previousMonthColor)) {
			m++;
		}

		if(Integer.parseInt(main.week.listLabelNumDaysWeek[0].getText())
				== firstDayWeek) {
			return;
		}
		else {

			if((Integer.parseInt(sourse.getText()) + m) <= 7) {
				int firstDayPreviousMonthNum = dayOfMonth(calendar.get(Calendar.MONTH) - 1) - m + 1;

				for (int i = 0; i < main.week.listLabelNumDaysWeek.length; i++) {
					if(firstDayPreviousMonthNum <= dayOfMonth((calendar.get(Calendar.MONTH) - 1))) {
						main.week.listLabelNumDaysWeek[i].setText(firstDayPreviousMonthNum + "");
						++firstDayPreviousMonthNum;
					}
					else {
						main.week.listLabelNumDaysWeek[i].setText(firstDayWeek + "");

						if (firstDayWeek < main.calendar.calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
							firstDayWeek++;
						else
							firstDayWeek = 1;
					}

					for (int j = 0; j < main.week.listLabelDays[i].length; j++) {
						main.week.listLabelDays[i][j].setText("");
						main.week.listLabelDays[i][j].setBackground(main.week.fc);

					}
				}

			}
			else {
				for (int i = 0; i < main.week.listLabelNumDaysWeek.length; i++) {

					main.week.listLabelNumDaysWeek[i].setText(firstDayWeek + "");

					if (firstDayWeek < main.calendar.calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
						firstDayWeek++;
					else
						firstDayWeek = 1;

					for (int j = 0; j < main.week.listLabelDays[i].length; j++) {
						main.week.listLabelDays[i][j].setText("");
						main.week.listLabelDays[i][j].setBackground(main.week.fc);

					}
				}
			}


			main.week.setCountObjInDayWeek(sourse.getText());
		}
//		for(SaveData sd : main.windowCN.listSaveObj) {
//			if(sourse.getText().equals(sd.day) && month.getText().equals(sd.month)) {
//				main.windowCN.panelLeftCNTop.setData(sd.day, sd.month, sd.year, sd.object,
//						sd.name, sd.time, sd.note);
//			}
//			else if(sourse.getText().equals(sd.day) && month.getText().equals(sd.month)) {
//				main.windowCN.panelLeftCNTop.setData(sd.day, sd.month, sd.year, sd.object,
//						sd.name, sd.time, sd.note);
//			}
//			else if(sourse.getText().equals(sd.day) && month.getText().equals(sd.month)) {
//				main.windowCN.panelLeftCNTop.setData(sd.day, sd.month, sd.year, sd.object,
//						sd.name, sd.time, sd.note);
//			}
//
//
//		}

	}




// Обработка событий при нажатии мыши на метке дня месяца
	public Label sourse, previousSourse;
	Color colorSourse;

//	@Override
//	public void mousePressed(MouseEvent e) {
//		sourse = (Label) e.getSource();
//
//		if (sourse.getBackground() == currentD)
//			colorSourse = currentD;
//
//		if (previousSourse != null) {
//			if (colorSourse == currentD) {
//				colorTodayDays();
//				colorSourse = null;
//			} else {
//				previousSourse.setBackground(ccc);
//			}
//		}
//
//
//// Подсветка дня месяца при нажатии
////		for(int i = 0; i < numMonth[flag].length; i++) {
////			if(sourse == numMonth[flag][i]) {
////				if(sourse.getBackground() != df) {
////					previousSourse = sourse;
////
////					sm.dayCh.select(i);
////					sm.monthCh.select(flag);
////
////					sourse.setBackground(df);
////
////				}
////
////
////			}
////
////		}
//	}








	// Метод создание чисел месяца
	// и добавление слушателя событий
	public void createNumMonth() {

//	"Январь":
				for (int j = 0; j < Months.January.getCountDays(); j++) {
					numMonth[0][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[0][j].addMouseListener(this);
				}

//	"Февраль":
				for (int j = 0; j < Months.Fibruary.getCountDays(); j++) {
					numMonth[1][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[1][j].addMouseListener(this);
				}

//	"Март":
				for (int j = 0; j < Months.March.getCountDays(); j++) {
					numMonth[2][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[2][j].addMouseListener(this);
				}

//	"Апрель":
				for (int j = 0; j < Months.April.getCountDays(); j++) {
					numMonth[3][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[3][j].addMouseListener(this);
				}

//	"Май":
				for (int j = 0; j < Months.May.getCountDays(); j++) {
					numMonth[4][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[4][j].addMouseListener(this);
				}

//	"Июнь":
				for (int j = 0; j < Months.June.getCountDays(); j++) {
					numMonth[5][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[5][j].addMouseListener(this);
				}

//	"Июль":
				for (int j = 0; j < Months.July.getCountDays(); j++) {
					numMonth[6][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[6][j].addMouseListener(this);
				}

//	"Август":
				for (int j = 0; j < Months.August.getCountDays(); j++) {
					numMonth[7][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[7][j].addMouseListener(this);
				}

//	"Сентябрь":
				for (int j = 0; j < Months.September.getCountDays(); j++) {
					numMonth[8][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[8][j].addMouseListener(this);
				}

//	"Октябрь":
				for (int j = 0; j < Months.October.getCountDays(); j++) {
					numMonth[9][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[9][j].addMouseListener(this);
				}

//	"Ноябрь":
				for (int j = 0; j < Months.November.getCountDays(); j++) {
					numMonth[10][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[10][j].addMouseListener(this);
				}

//	"Декабрь":
				for (int j = 0; j < Months.December.getCountDays(); j++) {
					numMonth[11][j] = new LabelCurrentMonth("" + (j + 1), FlowLayout.CENTER);
					numMonth[11][j].addMouseListener(this);
				}

	}




	// Метод добавление чисел месяца
//	public void insertNumMonth() {
//
//		switch (month.getText()) {
//
//			case "Январь":
//				for(int j = 24; j < numMonth[0].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[0].length; j++)
//					calNumb.add(numMonth[0][j]);
//
//				for(int j = 0; j < 4; j++)
//					calNumb.add(new labelPreviousMonth());
//				break;
//
//			case "Февраль":
//				for(int j = 28; j < numMonth[0].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[1].length; j++)
//					calNumb.add(numMonth[1][j]);
//
//				for(int j = 0; j < 11; j++)
//					calNumb.add(new labelPreviousMonth());
//				break;
//
//			case "Март":
//				for(int j = 25; j < numMonth[1].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[2].length; j++)
//					calNumb.add(numMonth[2][j]);
//
//				for(int j = 0; j < 8; j++)
//					calNumb.add(new labelPreviousMonth());
//				break;
//
//			case "Апрель":
//				for(int j = 25; j < numMonth[2].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[3].length; j++)
//					calNumb.add(numMonth[3][j]);
//
//				for(int j = 0; j < 6; j++)
//					calNumb.add(new labelPreviousMonth());
//				break;
//
//			case "Май":
//				for(int j = 0; j < 1; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[4].length; j++)
//					calNumb.add(numMonth[4][j]);
//
//				for(int j = 0; j < 10; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				break;
//
//			case "Июнь":
//				for(int j = 27; j < numMonth[4].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[5].length; j++)
//					calNumb.add(numMonth[5][j]);
////					System.out.println(numMonth[5].length);
//
//				for(int j = 0; j < 8; j++)
//					calNumb.add(new labelPreviousMonth());
//				break;
//
//			case "Июль":
//				for(int j = 24; j < numMonth[5].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[6].length; j++)
//					calNumb.add(numMonth[6][j]);
//
//				for(int j = 0; j < 5; j++)
//					calNumb.add(new labelPreviousMonth());
//				break;
//
//			case "Август":
//				for(int j = 29; j < numMonth[6].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[7].length; j++)
//					calNumb.add(numMonth[7][j]);
//
//				for(int j = 0; j < 9; j++)
//					calNumb.add(new labelPreviousMonth());
//				break;
//
//			case "Сентябрь":
//				for(int j = 26; j < numMonth[7].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[8].length; j++)
//					calNumb.add(numMonth[8][j]);
//
//				for(int j = 0; j < 7; j++)
//					calNumb.add(new labelPreviousMonth());
//				break;
//
//			case "Октябрь":
//				for(int j = 23; j < numMonth[8].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[9].length; j++)
//					calNumb.add(numMonth[9][j]);
//
//				for(int j = 0; j < 4; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				break;
//
//			case "Ноябрь":
//				for(int j = 28; j < numMonth[9].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[10].length; j++)
//					calNumb.add(numMonth[10][j]);
//
//				for(int j = 0; j < 9; j++)
//					calNumb.add(new labelPreviousMonth());
//				break;
//
//			case "Декабрь":
//				for(int j = 25; j < numMonth[10].length; j++)
//					calNumb.add(new labelPreviousMonth());
//
//				for (int j = 0; j < numMonth[11].length; j++)
//					calNumb.add(numMonth[11][j]);
//
//				for(int j = 0; j < 6; j++)
//					calNumb.add(new labelPreviousMonth());
//				break;
//
//		}
//	}


	public void insertNumMonth2() {

//	"Январь":
				for(int j = 24; j < numMonth[0].length; j++)
					calMonthNubm[0].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[0].length; j++)
					calMonthNubm[0].add(numMonth[0][j]);

				for(int j = 0; j < 4; j++)
					calMonthNubm[0].add(new labelPreviousMonth());

//	"Февраль":
				for(int j = 28; j < numMonth[0].length; j++)
					calMonthNubm[1].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[1].length; j++)
					calMonthNubm[1].add(numMonth[1][j]);

				for(int j = 0; j < 11; j++)
					calMonthNubm[1].add(new labelPreviousMonth());

//	"Март":
				for(int j = 25; j < numMonth[1].length; j++)
					calMonthNubm[2].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[2].length; j++)
					calMonthNubm[2].add(numMonth[2][j]);

				for(int j = 0; j < 8; j++)
					calMonthNubm[2].add(new labelPreviousMonth());

//	"Апрель":
				for(int j = 25; j < numMonth[2].length; j++)
					calMonthNubm[3].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[3].length; j++)
					calMonthNubm[3].add(numMonth[3][j]);

				for(int j = 0; j < 6; j++)
					calMonthNubm[3].add(new labelPreviousMonth());

//	"Май":
				for(int j = 0; j < 1; j++)
					calMonthNubm[4].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[4].length; j++)
					calMonthNubm[4].add(numMonth[4][j]);

				for(int j = 0; j < 10; j++)
					calMonthNubm[4].add(new labelPreviousMonth());


//	"Июнь":
				for(int j = 27; j < numMonth[4].length; j++)
					calMonthNubm[5].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[5].length; j++)
					calMonthNubm[5].add(numMonth[5][j]);

				for(int j = 0; j < 8; j++)
					calMonthNubm[5].add(new labelPreviousMonth());

//	"Июль":
				for(int j = 24; j < numMonth[5].length; j++)
					calMonthNubm[6].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[6].length; j++)
					calMonthNubm[6].add(numMonth[6][j]);

				for(int j = 0; j < 5; j++)
					calMonthNubm[6].add(new labelPreviousMonth());

//	"Август":
				for(int j = 29; j < numMonth[6].length; j++)
					calMonthNubm[7].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[7].length; j++)
					calMonthNubm[7].add(numMonth[7][j]);

				for(int j = 0; j < 9; j++)
					calMonthNubm[7].add(new labelPreviousMonth());

//	"Сентябрь":
				for(int j = 26; j < numMonth[7].length; j++)
					calMonthNubm[8].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[8].length; j++)
					calMonthNubm[8].add(numMonth[8][j]);

				for(int j = 0; j < 7; j++)
					calMonthNubm[8].add(new labelPreviousMonth());

//	"Октябрь":
				for(int j = 23; j < numMonth[8].length; j++)
					calMonthNubm[9].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[9].length; j++)
					calMonthNubm[9].add(numMonth[9][j]);

				for(int j = 0; j < 4; j++)
					calMonthNubm[9].add(new labelPreviousMonth());


//	"Ноябрь":
				for(int j = 28; j < numMonth[9].length; j++)
					calMonthNubm[10].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[10].length; j++)
					calMonthNubm[10].add(numMonth[10][j]);

				for(int j = 0; j < 9; j++)
					calMonthNubm[10].add(new labelPreviousMonth());

//	"Декабрь":
				for(int j = 25; j < numMonth[10].length; j++)
					calMonthNubm[11].add(new labelPreviousMonth());

				for (int j = 0; j < numMonth[11].length; j++)
					calMonthNubm[11].add(numMonth[11][j]);

				for(int j = 0; j < 6; j++)
					calMonthNubm[11].add(new labelPreviousMonth());

	}

}
