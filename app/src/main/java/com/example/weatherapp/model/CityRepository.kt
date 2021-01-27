package com.example.weatherapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.api.CityServices
import com.example.weatherapp.database.CityDao

class CityRepository(
        private val cityService: CityServices,
        private val cityDao: CityDao
) {
    fun getCities(): LiveData<List<City>> {
        return cityDao.getCities
    }

    suspend fun deleteCities(){
        cityDao.deleteAll()
    }

    suspend fun findCity(query: String) {
        try {
            val result = cityService.getCity(query, APP_ID)
            cityDao.insert(result)
        } catch (t: Throwable) {
            Log.e("TAG", t.message.toString())
        }
    }

    companion object {
        const val APP_ID = "cf7ef9156622ecccc8decb4a00a549b1"
    }
}