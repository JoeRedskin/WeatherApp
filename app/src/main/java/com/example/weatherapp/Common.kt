package com.example.weatherapp

object Common {
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    val cityService: CityServices
        get() = RetrofitClient.getClient(BASE_URL).create(CityServices::class.java)
}