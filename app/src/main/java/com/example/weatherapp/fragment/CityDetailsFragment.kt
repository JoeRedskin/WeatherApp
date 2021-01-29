package com.example.weatherapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityDetailsBinding

class CityDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCityDetailsBinding

    private val args: CityDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_city_details, container, false)
        setupWithNavController(binding.toolbar, findNavController())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.city = args.city
        Log.d("TAG", args.city.toString())
    }

}