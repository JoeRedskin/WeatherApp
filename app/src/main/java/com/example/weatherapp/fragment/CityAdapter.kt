package com.example.weatherapp.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CityItemBinding
import com.example.weatherapp.fragment.CityAdapter.CityViewHolder
import com.example.weatherapp.model.City


class CityAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<CityViewHolder>() {

    private val citiesList = mutableListOf<City>()

    interface OnItemClickListener {
        fun onItemClicked(city: City)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CityItemBinding>(inflater, R.layout.city_item, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(citiesList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClicked(citiesList[position]);
        }
    }

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