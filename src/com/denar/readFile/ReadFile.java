package com.denar.readFile;

import com.denar.Files.SaveData;
import com.denar.main.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;

public class ReadFile {

    Main main;
    public ArrayList<SaveData> listSaveObj = new ArrayList<>();

    public ReadFile(Main main) {
        this.main = main;

        readFile();

    }

    // Чтение данных из файла
    public void readFile() {

        String str;
        String object = null;
        String name = null;
        String month = null;
        String time = null;
        String day = null;
        String year = null;
        String note = null;

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
                                int flagDay = Integer.parseInt(day) - 1;
                                main.calendar.numMonth[flagMonth][flagDay].setBackground(main.calendar.df);
                                break;
                            case "Год ":
                                year = br.readLine();
                                break;
                            case "Время ":
                                time = br.readLine();
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

//        try {
//            Thread.sleep(100);
//            main.windowCN2.showInformationToday();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }




}
