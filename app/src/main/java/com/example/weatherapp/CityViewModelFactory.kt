package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CityViewModelFactory(private val cityRepository: CityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return CityViewModel(cityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}