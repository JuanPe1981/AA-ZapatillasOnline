package com.svalero.zapatillas.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private final static String DATE_PATTERN = "dd.MM.yyyy";
    private final static String FORMAT_DATE_PATTERN = "dd-MM-yyyy";

    //Pasa el String introducido por usuario con la fecha a formato Date (Según formato indicado).
    public static LocalDate parseLocalDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    //Pasa el valor Date devuelto por la BD a formato para mostrar por pantalla.
    public static String formatLocalDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(FORMAT_DATE_PATTERN));
    }

    //Paso de LocalDate java a Date java
    public static java.util.Date toDate(LocalDate localDate) {
        return java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    //Paso  de Date java a LocalDate java
    public static LocalDate toLocalDateFromJava(java.util.Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    //Paso de Date sql a LocalDate java
    public static LocalDate toLocalDateFromSql(java.sql.Date date) {
        return toLocalDateFromJava(toUtilDate(date));
    }

    //Paso de LocalDate java a Date sql
    public static java.sql.Date toSqlDate(LocalDate localDate) {
        return new java.sql.Date(toDate(localDate).getTime());
    }

    //Paso de Date java a Date sql
    public static java.sql.Date toSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date toUtilDate(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }
}
