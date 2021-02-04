package com.example.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.model.City

@Database(entities = [City::class], version = 9)
abstract class AppDatabase : RoomDatabase() {
    abstract val cityDao: CityDao
}