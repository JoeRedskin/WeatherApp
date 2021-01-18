package com.example.weatherapp

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.weatherapp.RetrofitClient.retrofit
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }
    private val cityName: TextView by lazy {
        findViewById(R.id.city_name)
    }
    private val cityTemp: TextView by lazy {
        findViewById(R.id.city_temperature)
    }
    private val db: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "populus-database").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }
    private val viewModel: CityViewModel by lazy {
        CityViewModel(CityRepository(retrofit, db.personDao))
    }
    private val citiesList = ArrayList<City>()
    private val adapter = CityAdapter(citiesList)
    private var tempType = "C"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter

        viewModel.citiesList.observe(this) {
            if (it != null) {
                if (it.isNotEmpty()) {
                    citiesList.clear()
                    citiesList.addAll(it)
                    updateCityInfo(citiesList[0])
                    adapter.notifyDataSetChanged()
                }
            }
        }

        //db.personDao.deleteAll();
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

    private fun updateCityInfo(city: City) {
        val temperature = Utils().convertTemperatureType(city.main.temp, tempType)
        val tempString = "${temperature.roundToInt()} °"
        cityName.text = city.name
        cityTemp.text = tempString
    }

}