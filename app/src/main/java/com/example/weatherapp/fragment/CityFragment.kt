package com.example.weatherapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityBinding
import com.example.weatherapp.model.City
import com.example.weatherapp.model.CityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CityFragment : Fragment() {

    private lateinit var binding: FragmentCityBinding
    private val viewModel by viewModel<CityViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_city, container, false)
        setupWithNavController(binding.toolbar, findNavController())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CityAdapter(object : CityAdapter.OnItemClickListener {
            override fun onItemClicked(city: City) {
                val action = CityFragmentDirections.actionCityFragmentToCityDetailsFragment(city.id!!)
                findNavController().navigate(action)
            }
        })

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.recyclerView.adapter = adapter

        setupMenu()
//        viewModel.deleteCities()
        viewModel.citiesList.observe(requireActivity()) {
            if (!it.isNullOrEmpty()) {
                adapter.updateList(it)
            }
        }
    }

    private fun setupMenu() {
        binding.toolbar.inflateMenu(R.menu.menu_search)
        val searchItem = binding.toolbar.menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.findCity(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }
}