package com.example.weatherapp.model

import androidx.lifecycle.ViewModel

class CityDetailsViewModel(id: Int, repository: CityRepository) : ViewModel() {
    val city = repository.getCity(id)
}