package com.denar.conteiners;



public enum Months {

    January(31), Fibruary(28), March(31), April(30), May(31), June(30), July(31), August(31), September(30), October(31),
    November(30), December(31);

    private int countDays;

    Months(int cd) {
        countDays = cd;
    }

    public int getCountDays() {
        return countDays;
    }

}
