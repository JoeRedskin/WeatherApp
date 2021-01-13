package com.example.weatherapp

class Utils {
     fun convertTemperatureType(temperature: Double, tempType: String): Double {
        return when (tempType) {
            "F" -> temperature * 1.8 - 459.67
            "C" -> temperature - 273.15
            else -> 0.0
        }
    }
}