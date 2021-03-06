package com.example.weatherapp.module

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.model.CityRepository
import com.example.weatherapp.api.CityServices
import com.example.weatherapp.database.AppDatabase
import com.example.weatherapp.database.CityDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Provide {
    //Retrofit providers
    fun provideGson(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    fun provideRetrofit(factory: Gson): CityServices {
        return Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create(factory))
                .build()
                .create(CityServices::class.java)
    }

    //Database providers
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "populus-database")
                .fallbackToDestructiveMigration()
                .build()
    }

    fun provideDao(database: AppDatabase): CityDao = database.cityDao

    //Repository provider
    fun provideUserRepository(services: CityServices, dao: CityDao): CityRepository =
            CityRepository(services, dao)
}