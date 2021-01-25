package com.example.weatherapp

import androidx.lifecycle.ViewModel

class CityViewModel(
        private val cityRepository: CityRepository
) : ViewModel() {

    val citiesList = cityRepository.getCities()
    val city = cityRepository.getLastCity()
    fun findCity(query: String) = cityRepository.findCity(query)
}