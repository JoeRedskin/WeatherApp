package com.example.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.CityAdapter.MyViewHolder
import com.example.weatherapp.databinding.CityItemBinding


class CityAdapter(private val citiesList: List<City>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CityItemBinding>(inflater, R.layout.city_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val city = citiesList[position]
        holder.binding.city = city
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    class MyViewHolder(val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root)
}