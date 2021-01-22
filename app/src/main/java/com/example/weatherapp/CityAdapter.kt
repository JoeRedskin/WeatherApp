package com.example.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.CityAdapter.MyViewHolder
import com.example.weatherapp.databinding.CityItemBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt


class CityAdapter(private val citiesList: List<City>) : RecyclerView.Adapter<MyViewHolder>() {

    private lateinit var tempType: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CityItemBinding>(inflater, R.layout.city_item, parent, false)
        tempType = parent.resources.getString(R.string.celsius)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val city = citiesList[position]
        val name = city.name
        val temp = tempType
        val temperature = Utils.convertTemperatureType(city.main.temp, temp)
        val information = "$name, ${temperature.roundToInt()} ° $tempType"
        val date = formatDate(city.date)
        holder.binding.info = information
        holder.binding.date = date
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

    class MyViewHolder(val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root)
}