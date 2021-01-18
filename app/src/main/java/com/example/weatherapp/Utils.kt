package com.example.weatherapp

class Utils {

//    enum class TempType(val type:String){
//        Celsius(type = "C"),
//    }

     fun convertTemperatureType(temperature: Double, tempType: String): Double {
        return when (tempType) {
            "C" -> temperature - 273.15
            else -> 0.0
        }
    }
}