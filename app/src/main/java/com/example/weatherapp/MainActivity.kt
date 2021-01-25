package com.example.weatherapp

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val viewModel by viewModel<CityViewModel>()
    private val citiesList = ArrayList<City>()
    private val adapter = CityAdapter(citiesList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.recyclerView.adapter = adapter

        viewModel.citiesList.observe(this) {
            if (!it.isNullOrEmpty()) {
                citiesList.clear()
                citiesList.addAll(it)
                adapter.updateList(citiesList)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
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
        return super.onCreateOptionsMenu(menu)
    }
}