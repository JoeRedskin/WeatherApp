package com.example.weatherapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [City::class], version = 9)
abstract class AppDatabase : RoomDatabase() {
    abstract val cityDao: CityDao
}