package com.example.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CityServices {
    @GET("weather")
    fun getCity(@Query("q") cityQuery: String, @Query("appid") app_id: String): Call<City>
}