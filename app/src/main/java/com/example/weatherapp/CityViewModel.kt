package com.example.weatherapp

import androidx.lifecycle.ViewModel

class CityViewModel constructor(
        private val cityRepository: CityRepository
) : ViewModel() {

    val citiesList = cityRepository.getCities()

    fun findCity(query: String) {
        cityRepository.findCity(query)
    }
}