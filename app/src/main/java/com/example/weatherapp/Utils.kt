package com.example.weatherapp

class Utils {

    enum class TempType(val type:String){
        Celsius(type = "C"),
        Fahrenheit(type = "F")
    }

     fun convertTemperatureType(temperature: Double, tempType: String): Double {
        return when (tempType) {
            "F" -> temperature * 1.8 - 459.67
            "C" -> temperature - 273.15
            else -> 0.0
        }
    }
}