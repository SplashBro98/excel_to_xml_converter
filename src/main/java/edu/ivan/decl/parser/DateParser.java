package edu.ivan.decl.parser;

import java.time.LocalDate;

public class DateParser {

    public LocalDate createDate(String input){
        int day = Integer.parseInt(input.substring(0,2));
        int month = Integer.parseInt(input.substring(3,5));
        int year = Integer.parseInt(input.substring(6,10));
        return LocalDate.of(year, month, day);
    }
}
