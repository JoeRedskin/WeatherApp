package com.example.weatherapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(DateConverter::class)
@Database(entities = [City::class], version = 7)
abstract class AppDatabase : RoomDatabase() {
    abstract val personDao: CityDao?
}