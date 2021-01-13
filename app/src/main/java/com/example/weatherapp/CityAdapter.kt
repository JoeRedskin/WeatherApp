package com.example.weatherapp

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.CityAdapter.MyViewHolder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

class CityAdapter(private val citiesList: ArrayList<City>, private val preferenceTemp: SharedPreferences?) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.city_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val city = citiesList[position]
        val name = city.name
        val tempType = preferenceTemp?.getString(MainActivity.TEMP_TYPE, "")
        val temperature = Utils().convertTemperatureType(city.temperature, tempType!!)
        val information = "$name, ${temperature.roundToInt()} ° $tempType"
        val date = formatDate(city.date)
        holder.cityInformation.text = information
        holder.cityDate.text = date
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    private fun formatDate(dateObject: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss")
        return formatter.format(dateObject)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cityInformation: TextView = itemView.findViewById(R.id.city_information)
        var cityDate: TextView = itemView.findViewById(R.id.city_date)
    }
}