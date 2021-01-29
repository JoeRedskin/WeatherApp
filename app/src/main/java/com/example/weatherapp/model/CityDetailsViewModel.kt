package com.example.weatherapp.model

import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CityDetailsViewModel(id: Int) : ViewModel(), KoinComponent {
    private val repository by inject<CityRepository>()
    val city = repository.getCity(id)
}