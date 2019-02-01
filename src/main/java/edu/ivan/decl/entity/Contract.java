package edu.ivan.decl.entity;

import java.time.LocalDate;

public class Contract {
    private LocalDate date;
    private String number;

    public Contract() {
    }

    public Contract(LocalDate date, String number) {
        this.date = date;
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
