package com.denar.conteiners;

import com.denar.main.Main;

import java.awt.*;

public class Week2 {

    Main main;

    public Panel week;
    public Panel pwWest, pwNorth, pwCenter, pwnTime, pwnDays;
    public Panel[] listPanelDays;
    public Panel wrapperCentrLPD, wrapperLPD;
    public Label time;
    public Label[] listLabelTime;

    public Button leftButton, rightButton;


    String[] nameDays = {"Понедельник", "Вторник", "Среда", "Четверг",
            "Пятница", "Суббота", "Воскресенье"};


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
            super.setPreferredSize(new Dimension(50, 32));
            super.setBackground(new Color(0x9CD4F0));
        }
    }


// Конструктор
    public Week2(Main main) {
        this.main = main;

        week = new Panel();						// создание панели для недели

        pwWest = new Panel();
        pwNorth = new Panel();
        pwCenter = new Panel();
        pwnTime = new Panel();
        pwnDays = new Panel();

//        listPanelDays = new Panel[];
        wrapperCentrLPD = new Panel();
        wrapperLPD = new Panel();

        leftButton = new Button("<");
        rightButton = new Button(">");

        listLabelTime = new Label[12];

        time = new Label("Время");
        time.setPreferredSize(new Dimension(50, 32));
        time.setAlignment(Label.CENTER);


// Основная панель недели
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
//        pwCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        pwCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));


// Кнопки переключения недели лево и право
        leftButton.setPreferredSize(new Dimension(20, 50));
        rightButton.setPreferredSize(new Dimension(20, 50));

// Центральная панель обертка для панели обертки списка дней недели
        wrapperCentrLPD.setPreferredSize(new Dimension(700,
                456));
        wrapperCentrLPD.setBackground(Color.yellow);

// Панель обертка дней недели
        wrapperLPD.setPreferredSize(new Dimension(700, 456));


// Добавление Лейблов дней недели и Лейбла Время в панель обертку для
// северной панели
        pwnTime.add(time);
        for(int i = 0; i < nameDays.length; i++) {
            pwnDays.add(new PwNorthLabel(nameDays[i], FlowLayout.CENTER));
        }

        pwNorth.add(pwnTime);
        pwNorth.add(pwnDays);


        int flag = 7;
// Добавление меток в Западную панель
        for(int i = 0; i < listLabelTime.length; i++) {
            listLabelTime[i] = new PwWestLabel(flag + ":00", FlowLayout.CENTER);
            pwWest.add(listLabelTime[i]);
            flag++;
        }


        wrapperCentrLPD.add(wrapperLPD);

        pwCenter.add(leftButton);
        pwCenter.add(wrapperCentrLPD);
        pwCenter.add(rightButton);

//// Добавление меток в Центральную панель
//        for(int i = 0; i < listPanelDays.length; i++) {
//            listPanelDays[i] = new Panel();
//            listPanelDays[i].setPreferredSize(new Dimension(100, 500));
//            listPanelDays[i].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 2));
//
//            for(int k = 0; k < listLabelDays[i].length; k++) {
//
//                listLabelDays[i][k] = new Label("" + k);
//
//                listLabelDays[i][k].setPreferredSize(new Dimension(100, 32));
//                listLabelDays[i][k].setBackground(eee);
//                listLabelDays[i][k].addMouseListener(this);
//                listPanelDays[i].add(listLabelDays[i][k]);
//
//
//            }
//
//            pwCenter.add(listPanelDays[i]);
//        }


        week.add(pwWest, BorderLayout.WEST);
        week.add(pwNorth, BorderLayout.NORTH);
        week.add(pwCenter, BorderLayout.CENTER);


// Конец Конструктора
    }



}