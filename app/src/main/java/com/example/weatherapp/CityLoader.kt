package com.example.weatherapp

import android.content.Context
import androidx.loader.content.AsyncTaskLoader
import com.example.weatherapp.DataQuery.fetchCityData

class CityLoader(context: Context?, var cityQuery: String?) : AsyncTaskLoader<City?>(context!!) {
    override fun onStartLoading() {
        forceLoad()
    }

    override fun loadInBackground(): City? {
        return if (cityQuery == null) null else fetchCityData(cityQuery!!)
    }
}