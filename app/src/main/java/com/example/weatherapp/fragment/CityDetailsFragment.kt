package com.example.weatherapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityDetailsBinding

class CityDetailsFragment : Fragment() {

    private val binding: FragmentCityDetailsBinding by lazy {
        DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_city_details)
    }
    private val args: CityDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        val city = args.city
        binding.city = city
        Log.d("TAG", city.toString())
    }

}