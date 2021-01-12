package com.example.weatherapp;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    @TypeConverter
    public static LocalDateTime toDate(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");
        return LocalDateTime.parse(timestamp, formatter);
    }

    @TypeConverter
    public static String toTimestamp(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");
        return date == null ? null : formatter.format(date);
    }
}
