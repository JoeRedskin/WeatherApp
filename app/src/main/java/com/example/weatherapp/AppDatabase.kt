package com.example.weatherapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(DateConverter::class)
@Database(entities = [City::class], version = 9)
abstract class AppDatabase : RoomDatabase() {
    abstract val cityDao: CityDao
}