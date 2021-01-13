package com.example.weatherapp

import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

object DataQuery {
    @JvmStatic
    fun fetchCityData(cityQuery: String): City? {
        var city: City? = null
        val JSON_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q="
        val URL_TAIL = "&appid=cf7ef9156622ecccc8decb4a00a549b1"
        try {
            val stringUrl = JSON_WEATHER_URL + cityQuery + URL_TAIL
            val url = URL(stringUrl)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()
            if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = httpURLConnection.inputStream
                val jsonResponse = readFromStream(inputStream)
                val baseJsonResponse = JSONObject(jsonResponse)
                val tempMain = baseJsonResponse.getJSONObject("main")
                val temp = tempMain.getDouble("temp")
                val name = baseJsonResponse.getString("name")
                city = City(name, temp)
            }
        } catch (e: Exception) {
            Log.e("fetchCityData", "Problem parsing the city JSON results", e)
        }
        return city
    }

    @Throws(IOException::class)
    private fun readFromStream(inputStream: InputStream?): String {
        val output = StringBuilder()
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream, StandardCharsets.UTF_8)
            val reader = BufferedReader(inputStreamReader)
            var line = reader.readLine()
            while (line != null) {
                output.append(line)
                line = reader.readLine()
            }
        }
        return output.toString()
    }
}