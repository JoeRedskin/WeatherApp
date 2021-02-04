package com.example.weatherapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CityViewModel() : ViewModel(), KoinComponent {
    private val repository by inject<CityRepository>()
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