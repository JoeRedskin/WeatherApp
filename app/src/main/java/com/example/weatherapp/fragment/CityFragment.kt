package com.example.weatherapp.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityBinding
import com.example.weatherapp.model.City
import com.example.weatherapp.model.CityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CityFragment : Fragment() {

    private val binding: FragmentCityBinding by lazy {
        DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_city)
    }

    private val viewModel by viewModel<CityViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        val toolbar = requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
//        requireActivity().setSupportActionController()
        toolbar.setupWithNavController(findNavController(),appBarConfiguration)

        val adapter = CityAdapter(object : CityAdapter.OnItemClickListener {
            override fun onItemClicked(city: City) {
                val action = CityFragmentDirections.actionCityFragmentToCityDetailsFragment(city)
                findNavController().navigate(action)
            }
        })
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.recyclerView.adapter = adapter

//        viewModel.deleteCities()
        viewModel.citiesList.observe(requireActivity()) {
            if (!it.isNullOrEmpty()) {
                adapter.updateList(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.findCity(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}