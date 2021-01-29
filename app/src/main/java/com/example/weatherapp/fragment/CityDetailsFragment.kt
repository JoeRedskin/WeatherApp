package com.example.weatherapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.weatherapp.R
import com.example.weatherapp.api.CityServices
import com.example.weatherapp.database.CityDao
import com.example.weatherapp.databinding.FragmentCityDetailsBinding
import com.example.weatherapp.model.CityDetailsViewModel
import com.example.weatherapp.model.CityRepository
import org.koin.android.ext.android.inject

class CityDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCityDetailsBinding

    private val cityDao: CityDao by inject()
    private val cityService: CityServices by inject()
    private val args: CityDetailsFragmentArgs by navArgs()

    //    private val viewModel by viewModel<CityDetailsViewModel> {
//        parametersOf(args.id)
//    }

    private val viewModel: CityDetailsViewModel by viewModels(
            factoryProducer = { CityDetailViewModelFactory(args.id, CityRepository(cityService, cityDao)) }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_city_details, container, false)
        binding.lifecycleOwner
        setupWithNavController(binding.toolbar, findNavController())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Log.d("tag", args.id.toString())
//        Log.d("TAG", viewModel.city.toString())
        binding.viewmodel = viewModel
    }

}