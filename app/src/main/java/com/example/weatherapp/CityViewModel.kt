package com.example.weatherapp

import androidx.lifecycle.ViewModel

class CityViewModel(
        private val cityRepository: CityRepository
) : ViewModel() {

    val citiesList = cityRepository.getCities()
    fun findCity(query: String) = cityRepository.findCity(query)
    fun deleteCities() = cityRepository.deleteCities()
}