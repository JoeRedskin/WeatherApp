package com.example.weatherapp

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

@Entity
data class City(
        @Expose
        var name: String,
        @Embedded
        @Expose
        val main: Main,
        @SerializedName("dt")
        @Expose
        var date: Long,
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null) {

    data class Main(
            @Expose
            val temp: Double
    )

    fun getInfo(): String {
        return "$name, ${convertTempToString()} °"
    }

    fun getDateToString(): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss")
        val time = date * 1000L
        val triggerTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time),
                TimeZone.getDefault().toZoneId())
        return formatter.format(triggerTime)
    }

    fun convertTempToString(): String {
        val tempString = this.main.temp - 273.15
        return tempString.roundToInt().toString()
    }
}