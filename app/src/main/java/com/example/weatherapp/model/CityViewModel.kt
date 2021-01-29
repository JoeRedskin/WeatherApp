package com.example.weatherapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CityViewModel(
        private val repository: CityRepository
) : ViewModel() {

    val citiesList = repository.getCities()

    fun findCity(query: String) {
        viewModelScope.launch {
            repository.findCity(query)
        }

    }

    fun deleteCities() {
        viewModelScope.launch {
            repository.deleteCities()
        }
    }
}