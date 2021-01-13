package com.example.weatherapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDateTime

@Entity
class City(name: String, temperature: Double) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var name = ""
    var temperature: Double
        private set

    @TypeConverters(DateConverter::class)
    var date: LocalDateTime

    init {
        this.name = name
        this.temperature = temperature
        date = LocalDateTime.now()
    }
}