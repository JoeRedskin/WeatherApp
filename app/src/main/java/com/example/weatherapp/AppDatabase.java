package com.example.weatherapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {City.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CityDao getPersonDao();
}
