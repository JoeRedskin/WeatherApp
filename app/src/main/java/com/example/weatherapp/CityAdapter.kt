package com.example.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.CityAdapter.CityViewHolder
import com.example.weatherapp.databinding.CityItemBinding


class CityAdapter: RecyclerView.Adapter<CityViewHolder>() {

    private val citiesList = mutableListOf<City>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CityItemBinding>(inflater, R.layout.city_item, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) =
            holder.bind(citiesList[position])

    override fun getItemCount(): Int = citiesList.size

    fun updateList(cities: List<City>) {
        val cityDiffUtilCallback = CityDiffUtilCallback(cities)
        val cityDiffResult = DiffUtil.calculateDiff(cityDiffUtilCallback)
        cityDiffResult.dispatchUpdatesTo(this)
        citiesList.clear()
        citiesList.addAll(cities)
    }

    class CityViewHolder(private val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.city = city
            binding.executePendingBindings()
        }
    }

    inner class CityDiffUtilCallback(private val newList: List<City>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = citiesList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldCity = citiesList[oldItemPosition]
            val newCity = newList[newItemPosition]
            return oldCity.id === newCity.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldCity = citiesList[oldItemPosition]
            val newCity = newList[newItemPosition]
            return oldCity == newCity
        }
    }
}