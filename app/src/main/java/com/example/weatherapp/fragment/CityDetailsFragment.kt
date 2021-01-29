package com.example.weatherapp.fragment

import android.os.Bundle
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
import com.example.weatherapp.model.CityDetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CityDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCityDetailsBinding
    private val args: CityDetailsFragmentArgs by navArgs()

    private val viewModel by viewModel<CityDetailsViewModel> {
        parametersOf(args.id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_city_details, container, false)

        setupWithNavController(binding.toolbar, findNavController())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Log.d("tag", args.id.toString())
//        Log.d("TAG", viewModel.city.toString())
        binding.lifecycleOwner
        binding.viewmodel = viewModel
    }

}