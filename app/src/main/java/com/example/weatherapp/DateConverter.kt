package com.example.weatherapp

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object DateConverter {
    @JvmStatic
    @TypeConverter
    fun toDate(timestamp: Long): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss")
        return LocalDateTime.parse(timestamp.toString(), formatter)
    }

    @JvmStatic
    @TypeConverter
    fun toTimestamp(date: LocalDateTime): Long {
        return date.toEpochSecond(ZoneOffset.UTC)
    }
}