package com.denar.conteiners;

import com.denar.Files.SaveData;
import com.denar.main.Main;
import com.denar.readFile.ReadFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;

public class WindowCreateNote implements ActionListener {

    Main main;

    public Panel panelCreateNote, panelCNBottom;
    public PanelCNTop panelCNTop;
    public Panel panelLeftLabelCNTop, panelRightLabelCNTop;


    public Button buttonCN;

    public String wcObject, wcName, wcDate, wcTime, wcNote;

    public String[] data = new String[7];



    public ArrayList<SaveData> listSaveObj = new ArrayList<>();



   public class PanelCNTop extends Panel {
//       panelCNTop(String obj, String name, String date, String time) {
//           this.setPreferredSize(new Dimension(250, 248));
////           this.setBackground(Color.yellow);
//
//           data[0] = obj;
//           data[1] = name;
//           data[2] = date;
//           data[3] = time;
//
//       }

       public PanelCNTop() {
           this.setPreferredSize(new Dimension(499, 248));
       }

// Установка данных переменных
       public void setData(String day, String month, String year,
                           String obj, String name, String time, String note) {
           data[0] = (day == null) ? "..." : day;
           data[1] = (month == null) ? "..." : month;
           data[2] = (year == null) ? "..." : year;
           data[3] = (obj == null) ? "..." : obj;
           data[4] = (name == null) ? "..." : name;
           data[5] = (time == null) ? "..." : time;
           data[6] = (note == null) ? "..." : note;

           repaint();
       }

// Отрисовка данных в панели
       @Override
       public void paint(Graphics g) {
           super.paint(g);
           g.drawString("Объект: ", 10, 20);
           g.drawString(data[0] + " " + data[1] + " " + data[2], 100, 80);

           g.drawString("Имя: ", 10, 50);
           g.drawString(data[3], 100, 20);

           g.drawString("Дата: ", 10, 80);
           g.drawString(data[4], 100, 50);

           g.drawString("Время: ", 10, 110);
           g.drawString(data[5], 100, 110);

           g.drawString("Примечание: ", 250, 20);
           g.drawString(data[6], 250, 40);


       }

// Конец Внутреннего класса
   }




// Начало Конструктора
    public WindowCreateNote(Main main) {

        this.main = main;

        panelCNTop = new PanelCNTop();

        panelCreateNote = new Panel();

        panelCNBottom = new Panel();

        buttonCN = new Button("+ Новая Заметка");


        panelCreateNote.setBackground(new Color(0xfcfcfc));
        panelCreateNote.setBounds(0, 0, 499, 298);
        panelCreateNote.setLayout(new BorderLayout());


        panelCNBottom.setPreferredSize(new Dimension(499, 50));
        panelCNBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));



        buttonCN.setPreferredSize(new Dimension(120, 30));
        buttonCN.setBackground(new Color(0xcccccc));
        buttonCN.addActionListener(this);


        panelCNBottom.add(buttonCN);

        panelCreateNote.add(panelCNTop, BorderLayout.CENTER);
        panelCreateNote.add(panelCNBottom, BorderLayout.SOUTH);




// Конец Конструктора
   }



// Метод читает файл и записывает в объект данные и создает массив объектов
    public void readFile() {
        String str;
        String object = null;
        String name = null;
        String month = null;
        String time = null;
        String day = null;
        String year = null;
        String note = null;

        int countObj = 1;
        String newTime = null;
        String priviousTime = null;

        int flagDay = 0;


        try(BufferedReader br = new BufferedReader (new FileReader("Data.txt"))) {

            while((str = br.readLine()) != null) {

// Условие при чтении файла если месяц равен текущему или предыдущему или
// следующему тогда записываются данные об объектах
                if(str.equals(main.sideMenu.nameMonth[main.calendar.calendar.get(Calendar.MONTH) - 1]) ||
                        str.equals(main.sideMenu.nameMonth[main.calendar.calendar.get(Calendar.MONTH)]) ||
                        str.equals(main.sideMenu.nameMonth[main.calendar.calendar.get(Calendar.MONTH) + 1])){


                    int flagMonth = 0;
                    for(int i = 0; i < main.sideMenu.nameMonth.length; i++) {
                        if(str.equals(main.sideMenu.nameMonth[i]))
                            flagMonth = i;
                    }
                    month = str;

                    boolean flagLoop = true;

                    do {

                        switch (str = br.readLine()) {
                            case "День ":
                                day = br.readLine();
                                flagDay = Integer.parseInt(day);
//                                main.calendar.numMonth[flagMonth][flagDay].setBackground(main.calendar.df);

                                break;
                            case "Год ":
                                year = br.readLine();
                                break;
                            case "Время ":
                                time = br.readLine();
                                main.calendar.setBackroundColorLabelRF(flagMonth, flagDay, time);
                                break;
                            case "Объект ":
                                object = br.readLine();
                                break;
                            case "Имя ":
                                name = br.readLine();
                                break;
                            case "Примечание ":
                                note = br.readLine();
                                break;
                            default:
//                                if (day.equals((main.calendar.currentNumDays + 1) + "") &&
//                                        month.equals(main.calendar.monthList[main.calendar.currentMonth])) {
//                        //                ++countObj;
//                                    newTime = time;
//                                    if(newTime.equals(priviousTime)) {
//                                        countObj++;
//                                        main.week.listLabelDays[4][0].setText(countObj + "");
//                                    }
//                                    else {
//                                        priviousTime = time;
//                                    }
//
//                                    System.out.println(countObj);
//                                }


                        }



                        if(str.equals("&end;"))
                            flagLoop = false;
                    } while (flagLoop);

                    listSaveObj.add(new SaveData(main, day, month, year, object, name, time, note));

                }
            }


        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }


    // Метод для показа количества информаци(заданий) на одно и то же время
    // сегоднешнего числа
//     public int countObjToday() {
//
//        int countObj = 1;
//        String time = null;
//        String priviousTime = null;
//
//        for (SaveData sd : listSaveObj) {
//            if (sd.day.equals((main.calendar.currentNumDays + 1) + "") &&
//                    sd.month.equals(main.calendar.monthList[main.calendar.currentMonth])) {
////                ++countObj;
//                time = sd.time;
//                if(time.equals(priviousTime)) {
//                    countObj++;
//                }
//                else {
//                    priviousTime = time;
//                }
//            }
//        }
//
//
////           if (d != null && m != null) {
////               for (SaveData sd : listSaveObj) {
////                   if (sd.day.equals(d) && sd.month.equals(m) &&
////                           sd.time.equals(t)) {
////                       setData(sd.day, sd.month, sd.year, sd.object,
////                               sd.name, sd.time, sd.note);
////
////                   }
////               }
////           }
////           else {
////
////               String curND = (main.calendar.currentNumDays + 1) + "";
////
////               for (SaveData sd : listSaveObj) {
////                   if (sd.day.equals(curND) &&
////                           sd.month.equals(main.calendar.monthList[main.calendar.currentMonth] + "")) {
////                       setData(sd.day, sd.month, sd.year, sd.object,
////                               sd.name, sd.time, sd.note);
////                   }
////                   else {
////                       setData(null, null, null,
////                               null, null, null, null);
////                   }
////               }
////           }
//        return countObj;
//     }




// Обработка событий при нажатии на кнопку
    @Override
    public void actionPerformed(ActionEvent e) {


        panelCreateNote.setVisible(false);

        main.sideMenu.panelSideMenu.setVisible(true);


        main.panelTop.validate();
    }




// Конец Класса
}
