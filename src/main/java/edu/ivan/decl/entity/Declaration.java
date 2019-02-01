package edu.ivan.decl.entity;

import java.math.BigDecimal;

public class Declaration {
    private static final int TAX_NUMBER = 105;
    private static String UNP = "190901743";
    private static int year = 2018;
    private static int period = 4;
    private static Director director = new Director("Бонадысенко О.В.");
    private static Double quartalProfit;

    public Declaration() {
    }

    public static int getTaxNumber() {
        return TAX_NUMBER;
    }

    public static String getUNP() {
        return UNP;
    }

    public static void setUNP(String UNP) {
        Declaration.UNP = UNP;
    }

    public static int getYear() {
        return year;
    }

    public static void setYear(int year) {
        Declaration.year = year;
    }

    public static int getPeriod() {
        return period;
    }

    public static void setPeriod(int period) {
        Declaration.period = period;
    }

    public static Director getDirector() {
        return director;
    }

    public static void setDirector(Director director) {
        Declaration.director = director;
    }

    public static Double getQuartalProfit() {
        return quartalProfit;
    }

    public static void setQuartalProfit(Double quartalProfit) {
        Declaration.quartalProfit = quartalProfit;
    }
}
