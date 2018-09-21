package com.denar.conteiners;

import com.denar.Files.SaveData;
import com.denar.main.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.Calendar;

public class SideMenu implements ActionListener, ItemListener {

	Main main;

	public Panel panelSideMenu, psm1, psm2, psm3;
	public Label object, name, date, time;
	public TextField objectTF, dateTF;
	public TextArea noteTA;
	public Choice nameCh, dayCh, monthCh, yearCh;
	public List timeList;

//	public ArrayList<SaveData> listSaveObj = new ArrayList<>();

// Календарь
	Calendar calendar = Calendar.getInstance();


	public Button save, cancel;

	int[] houres = {7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};

	String[] numMonth = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
			, "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"
			, "23", "24", "25", "26", "27", "28", "29", "30", "31"};

	public String[] nameMonth = {"Январь", "Февраль", "Март", "Апрель",
			"Май", "Июнь", "Июль", "Август",
			"Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

	public String[] year = {"2018", "2019", "2020", "2021"};

	// Конструктор Бокового Меню (БМ)
	public SideMenu(Main main) {

		this.main = main;


// Родительская панель	(РП)
		panelSideMenu = new Panel();


// Подпанели
		psm1 = new Panel();				// панель меток и текстовых полей
		psm2 = new Panel();				// панель примечания
		psm3 = new Panel();

// Кнопки
		save = new Button("Сохранить");
		cancel = new Button("Отмена");

// Метки
		object = new Label("Объект:");	// создание объектов меток
		name = new Label("Имя:");
		date = new Label("Дата:");
		time = new Label("Время:");


// Текстовые поля, список времени, текстовое поле
		objectTF = new TextField(20);	// создание объектов текстовых полей
		dateTF = new TextField(20);
		timeList = new List(3, false);
		noteTA = new TextArea("Примечание...", 10, 30, TextArea.SCROLLBARS_VERTICAL_ONLY);


// Меню имен
		nameCh = new Choice();
		dayCh = new Choice();
		monthCh = new Choice();
		yearCh = new Choice();




// Параметры РП
		panelSideMenu.setLayout(new BorderLayout());
		panelSideMenu.setBackground(new Color(0xBFDA86));
		panelSideMenu.setBackground(new Color(0xfcfcfc));
		panelSideMenu.setBounds(0, 0, 499, 298);


// Параметры панелей меток, примечания и кнопок
		psm1.setPreferredSize(new Dimension(249, 229));
//		psm1.setBackground(Color.PINK);
		psm1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));



		psm2.setPreferredSize(new Dimension(249, 229));
//		psm2.setBackground(new Color(0x94CDE9));
		psm2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

		// TextArea во второю малую панель

		psm3.setPreferredSize(new Dimension(499, 69));
		psm3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
//		psm3.setBackground(new Color(0x727272));





// Установка размеров кнопок
		save.setPreferredSize(new Dimension(70, 25));
		save.setBackground(new Color(0xcccccc));
		save.addActionListener(this);

		cancel.setPreferredSize(new Dimension(70, 25));
		cancel.setBackground(new Color(0xcccccc));
		cancel.addActionListener(this);


// Установка размеров меток
		object.setPreferredSize(new Dimension(70, 20));
		name.setPreferredSize(new Dimension(70, 20));
		time.setPreferredSize(new Dimension(70, 20));




// Добавление имен в меню имен
		nameCh.add("...");
		nameCh.add("Денис Архипов");
		nameCh.add("Игорь Фоломеев");
		nameCh.add("Игорь Колотихин");


// Добавление числа месяца
		for(int i = 0; i < numMonth.length; i++)
			dayCh.add(numMonth[i]);
		dayCh.select(calendar.get(Calendar.DAY_OF_MONTH) - 1);
//		dayCh.addItemListener(new EventItemListener(main));

// Добавление месяца
		for(int i = 0; i < nameMonth.length; i++)
			monthCh.add(nameMonth[i]);
		monthCh.select(calendar.get(Calendar.MONTH));
//		monthCh.addItemListener(new EventItemListener(main));
//		monthCh.setPreferredSize(new Dimension(70, 20));

// Добавление года
		for(int i = 0; i < year.length; i++)
			yearCh.add(year[i]);


// Добавление часов
		for(int i = 0; i < houres.length; i++)
			timeList.add(houres[i] + ":00");

//		timeList.addItemListener(this);

//		timeList.select(0);



		psm1.add(object);			// Добавление меток и текстовых полей
		psm1.add(objectTF);		// в первую малую панель
		psm1.add(name);
		psm1.add(nameCh);
		psm1.add(date);
		psm1.add(dayCh);
		psm1.add(monthCh);
		psm1.add(yearCh);
		psm1.add(time);
		psm1.add(timeList);


		psm2.add(noteTA);

		psm3.add(save);
		psm3.add(cancel);


		panelSideMenu.add(psm1, BorderLayout.CENTER);	// Добавление первой и второй малой панели БМ
		panelSideMenu.add(psm2, BorderLayout.EAST);		// в основную панель БМ
		panelSideMenu.add(psm3, BorderLayout.SOUTH);


//Конец Контейнера
	}







// Путь к файлу сериализации
//	private static final String PATH = "C:\\Users\\Denis\\Dropbox\\WeeklyNote2\\src\\com\\denar\\Files\\SaveData\\data.bin";

	public final String PATH = "SaveData.bin";

// Сериализация данных (Запись в файл)
	private void serialize(SaveData sd) {
		try(ObjectOutputStream objectOutputStream =
					new ObjectOutputStream(new FileOutputStream(PATH))) {

			objectOutputStream.writeObject(sd);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


// Десериализация данных (Распаковка файла)
	private void deserialize() {
		try(ObjectInputStream objectInputStream =
					new ObjectInputStream(new FileInputStream(PATH))) {

			SaveData sd = (SaveData) objectInputStream.readObject();
			System.out.println(sd);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


// Псевдодесериализация объета, в объект записываются данные из файла
//	private void pseusoDeserializeObject(String d, String m, String y, String o, String n, String t, String note) {
//		SaveData sd = new SaveData(d, m, y, o, n, t, note);
//		System.out.println(sd);
//	}


// Запись в файл данных при нажатии save
	private void writeFile(String month, String day, String year, String time, String object, String name, String comment) {

		String separator = System.getProperty("line.separator");	// Перенос строки в файле
		int flagMonth = 0;

		String datas = separator + month + separator +
				"День " + separator + day + separator + "Год " + separator +
				year + separator + "Время " + separator + time + separator +
				"Объект " + separator + object + separator + "Имя " + separator +
				name + separator + "Примечание " + separator + comment + separator +
				"&end;" + separator;

		char[] buffer = new char[datas.length()];		// Массив символов
		datas.getChars(0, datas.length(), buffer, 0);	// Разбивка строки datas -
																		// на символы



		try(FileWriter fw = new FileWriter("Data.txt", true)) {	// Символьный поток для записи в файл

			for(int i = 0; i < buffer.length; i++)
				fw.write(buffer[i]);



			for(int i = 0; i < nameMonth.length; i++) {
				if(monthCh.getSelectedItem().equals(nameMonth[i]))
					flagMonth = i;
			}




			main.windowCN2.listSaveObj.add(new SaveData(main, dayCh.getSelectedItem(), monthCh.getSelectedItem(),
					yearCh.getSelectedItem(), objectTF.getText(), nameCh.getSelectedItem(),
					timeList.getSelectedItem(), noteTA.getText()));

			if(main.calendar.numMonth[flagMonth][Integer.parseInt(day) - 1].getBackground() !=
					main.calendar.currentD)
				main.calendar.numMonth[flagMonth][Integer.parseInt(day) - 1].setBackground(main.calendar.df);


//			main.week.setColorInWeekNote(flagMonth, Integer.parseInt(day), time);
			main.week.setCountObjInDayWeek(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}


	}




	// Метод для установки времени в списке элементов timeList
	public void setTimeList(int c) {
		timeList.select(c);
	}


	// Обработка события от списка элементов
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(cancel)) {
			//		main.panelTop.add(new WindowCreateNote(main).panelCreateNote);
			//		main.panelTop.remove(panelSideMenu);


			objectTF.setText(null);
			nameCh.select(0);
			dayCh.select(main.calendar.currentNumDays);
			monthCh.select(main.calendar.currentMonth);
			yearCh.select(main.calendar.calendar.get(Calendar.YEAR) + "");
			timeList.select(0);
			noteTA.setText("Примечание...");


			panelSideMenu.setVisible(false);
			main.windowCN2.panelCreateNote.setVisible(true);

			main.panelTop.validate();


		}
		if(e.getSource().equals(save)) {

//			this.serialize(new SaveData(dayCh.getSelectedItem() + " ",
//					monthCh.getSelectedItem() + " ",
//					yearCh.getSelectedItem() + "\n",
//					object.getText() + " " + objectTF.getText() + "\n",
//					nameCh.getSelectedItem() + "\n",
//					time.getText() + " " + timeList.getSelectedItem()));

//					(main.sideMenu.dayCh.getSelectedItem() + " ",
//					main.sideMenu.monthCh.getSelectedItem() + " ",
//					main.sideMenu.yearCh.getSelectedItem() + "\n",
//					main.sideMenu.object.getText() + "\n",
//					main.sideMenu.nameCh.getSelectedItem() + "\n",
//					main.sideMenu.time.getText() + "");


//			this.serialize(sd);


//			writeFile(dayCh.getSelectedItem(), monthCh.getSelectedItem(),
//			yearCh.getSelectedItem(), timeList.getSelectedItem(),
//					objectTF.getText(), nameCh.getSelectedItem(), noteTA.getText());



			writeFile(monthCh.getSelectedItem(), dayCh.getSelectedItem(),
					yearCh.getSelectedItem(), timeList.getSelectedItem(),
					objectTF.getText(), nameCh.getSelectedItem(), noteTA.getText());

			objectTF.setText(null);
			nameCh.select(0);
			dayCh.select(main.calendar.currentNumDays);
			monthCh.select(main.calendar.currentMonth);
			yearCh.select(main.calendar.calendar.get(Calendar.YEAR) + "");
			timeList.select(1);
			noteTA.setText("Примечание...");


//			for(int i = 0; i < main.week.listLabelNumDaysWeek.length; i++) {
//				if(main.week.listLabelNumDaysWeek[i].getText().equals(dayCh.getSelectedItem())) {
//					int h = 0;
//
//					switch(timeList.getSelectedItem()) {
//						case "7:00":
//							h = 0;
//							break;
//						case "8:00":
//							h = 1;
//							break;
//						case "9:00":
//							h = 2;
//							break;
//						case "10:00":
//							h = 3;
//							break;
//						case "11:00":
//							h = 4;
//							break;
//						case "12:00":
//							h = 5;
//							break;
//						case "13:00":
//							h = 6;
//							break;
//						case "14:00":
//							h = 7;
//							break;
//						case "15:00":
//							h = 8;
//							break;
//						case "16:00":
//							h = 9;
//							break;
//						case "17:00":
//							h = 10;
//							break;
//						case "18:00":
//							h = 11;
//							break;
//					}
//
//					main.week.listLabelDays[i][h].setBackground(main.calendar.df);
//				}
//			}


			panelSideMenu.setVisible(false);
			main.windowCN2.panelCreateNote.setVisible(true);

			main.panelTop.validate();


//			this.deserialize();

//            showInformation();



		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}


//	protected void finalize() {
//		System.out.println("Обоъект SideMenu уничтожен");
//	}


// Конец Класса
}
