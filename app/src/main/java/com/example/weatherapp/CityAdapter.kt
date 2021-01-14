package com.example.weatherapp

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.CityAdapter.MyViewHolder
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

class CityAdapter(private val citiesList: ArrayList<City>, private val preferenceTemp: SharedPreferences) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.city_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val city = citiesList[position]
        val name = city.name
        val tempType = preferenceTemp.getString(MainActivity.TEMP_TYPE, "").toString()
        val temperature = Utils().convertTemperatureType(city.main.temp, tempType)
        val information = "$name, ${temperature.roundToInt()} ° $tempType"
        val date = formatDate(city.date)
        holder.cityInformation.text = information
        holder.cityDate.text = date
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    private fun formatDate(timestamp: Long): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss")
        val time = timestamp * 1000L
        val triggerTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time),
                TimeZone.getDefault().toZoneId())
        return formatter.format(triggerTime)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cityInformation: TextView = itemView.findViewById(R.id.city_information)
        var cityDate: TextView = itemView.findViewById(R.id.city_date)
    }
}