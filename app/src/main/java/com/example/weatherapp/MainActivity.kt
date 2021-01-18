package com.example.weatherapp

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.edit
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.weatherapp.RetrofitClient.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    private val switchCompat: SwitchCompat by lazy {
        findViewById(R.id.switch_temperature)
    }
    private val preferenceTemp: SharedPreferences by lazy {
        getSharedPreferences(TEMP_PREF, MODE_PRIVATE)
    }

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "populus-database").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }
    private val mService: CityServices = retrofit
    private val citiesList = ArrayList<City>()
    private val adapter = CityAdapter(citiesList)
    private var tempType = "C"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val hasVisited = preferenceTemp.getBoolean(HAS_VISITED, false)

        if (!hasVisited) {
            preferenceTemp.edit {
                putBoolean(HAS_VISITED, true)
                putString(TEMP_TYPE, "C")
            }
        }
        tempType = preferenceTemp.getString(TEMP_TYPE, "").toString()

        recyclerView.adapter = adapter
        adapter.updateTempUnit(tempType)
        if (tempType == "F") switchCompat.isChecked = false
        switchCompat.setOnCheckedChangeListener { _, isChecked ->
            tempType = if (isChecked) "C" else "F"
            preferenceTemp.edit {
                putString(TEMP_TYPE, tempType)
            }
            if (citiesList.isNotEmpty()) {
                updateCityInfo(citiesList[0])
            }
            adapter.updateTempUnit(tempType)
        }

//        db.personDao.deleteAll();
    }

    override fun onStart() {
        super.onStart()
        val everyone = db.personDao.allCities
        if (everyone.isNotEmpty()) {
            citiesList.clear()
            citiesList.addAll(everyone)
            adapter.notifyDataSetChanged()
            updateCityInfo(citiesList[0])
        }
    }

    override fun onStop() {
        super.onStop()
        if (citiesList.isNotEmpty()) {
            for (i in citiesList.indices.reversed()) {
                val city = citiesList[i]
                if (city.id == null) db.personDao.insert(city)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getCity(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun getCity(query: String) {
        mService.getCity(query, APP_ID).enqueue(object : Callback<City> {
            override fun onFailure(call: Call<City>, t: Throwable) {
                Log.e("TAG", t.message.toString())
            }

            override fun onResponse(call: Call<City>, response: Response<City>) {
                val newCity = response.body()
                if (newCity != null) {
                    citiesList.add(0, newCity)
                    adapter.notifyDataSetChanged()
                    updateCityInfo(newCity)
                } else {
                    Toast.makeText(this@MainActivity,
                            "City not found: $query",
                            Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun updateCityInfo(city: City) {
        val tempType = preferenceTemp.getString(TEMP_TYPE, "").toString()
        val temperature = Utils().convertTemperatureType(city.main.temp, tempType)
        val tempString = "${temperature.roundToInt()} °"
        cityName.text = city.name
        cityTemp.text = tempString
    }

    companion object {
        const val HAS_VISITED = "hasVisited"
        const val TEMP_PREF = "temperature"
        const val TEMP_TYPE = "temperatureType"
        const val APP_ID = "cf7ef9156622ecccc8decb4a00a549b1"
    }
}