package com.example.weatherapp

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateConverter {
    @JvmStatic
    @TypeConverter
    fun toDate(timestamp: String?): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss")
        return LocalDateTime.parse(timestamp, formatter)
    }

    @JvmStatic
    @TypeConverter
    fun toTimestamp(date: LocalDateTime?): String? {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss")
        return if (date == null) null else formatter.format(date)
    }
}