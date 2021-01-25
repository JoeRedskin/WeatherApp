package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityRepository(
        private val cityService: CityServices,
        private val cityDao: CityDao
) {
    fun getCities(): LiveData<List<City>> {
        return cityDao.getCities
    }

    fun findCity(query: String) {
        cityService.getCity(query, APP_ID).enqueue(object : Callback<City> {
            override fun onFailure(call: Call<City>, t: Throwable) {
                Log.e("TAG", t.message.toString())
            }

            override fun onResponse(call: Call<City>, response: Response<City>) {
                val newCity = response.body()
                if (newCity != null) {
                    cityDao.insert(newCity)
                    Log.d("VALUE", newCity.toString())

                } else {
                    Log.e("TAG", "City not found: $query")
                }
            }
        })
    }

    companion object {
        const val APP_ID = "cf7ef9156622ecccc8decb4a00a549b1"
    }
}