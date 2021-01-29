package com.example.weatherapp.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.model.CityDetailsViewModel
import com.example.weatherapp.model.CityRepository

class CityDetailViewModelFactory(private val id: Int, private val repository: CityRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityDetailsViewModel::class.java)) {
            return CityDetailsViewModel(id, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}