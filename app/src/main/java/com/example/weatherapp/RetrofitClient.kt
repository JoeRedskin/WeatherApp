package com.example.weatherapp

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    val retrofit: CityServices = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CityServices::class.java)
}

