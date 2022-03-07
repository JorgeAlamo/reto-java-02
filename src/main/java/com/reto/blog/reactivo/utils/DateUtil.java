package com.reto.blog.reactivo.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static int calculateAge(LocalDate date) {
        return Period.
                between(date, LocalDate.now())
                .getYears();
    }

    public static boolean isSameDay(LocalDate date1, LocalDate date2) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return fmt.format(date1)
                .equals(fmt.format(date2));
    }

}