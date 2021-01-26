package com.example.weatherapp

import retrofit2.http.GET
import retrofit2.http.Query

interface CityServices {
    @GET("weather")
    suspend fun getCity(@Query("q") cityQuery: String, @Query("appid") app_id: String): City
}