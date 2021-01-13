package com.example.weatherapp

import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import java.util.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<City?> {
    var citiesList = ArrayList<City>()
    var adapter: CityAdapter? = null
    var recyclerView: RecyclerView? = null
    var cityName: TextView? = null
    var cityTemp: TextView? = null
    var cityQuery: String? = null
    private var switchCompat: SwitchCompat? = null
    var tempType = "C"
    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityName = findViewById(R.id.city_name)
        cityTemp = findViewById(R.id.city_temperature)
        recyclerView = findViewById(R.id.recycler_vew)

//        recyclerView.itemAnimator = DefaultItemAnimator()
        adapter = CityAdapter(citiesList, tempType)
        recyclerView?.adapter = adapter
        switchCompat = findViewById(R.id.switch_temperature)
        switchCompat!!.setOnCheckedChangeListener { _, isChecked ->
            tempType = if (isChecked) "C" else "F"
            if (citiesList.isNotEmpty()) {
                updateCityInfo(citiesList[0])
            }
            adapter!!.notifyDataSetChanged()
        }

        db = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "populus-database").allowMainThreadQueries().fallbackToDestructiveMigration().build()

        //db?.personDao?.deleteAll();

    }

    override fun onStart() {
        super.onStart()
        val everyone = db!!.personDao?.allCities
        if (everyone!!.isNotEmpty()) {
            citiesList.clear()
            citiesList.addAll(everyone)
            adapter!!.notifyDataSetChanged()
            updateCityInfo(citiesList[0])
        }
    }

    override fun onStop() {
        super.onStop()
        if (citiesList.isNotEmpty()) {
            for (i in citiesList.indices.reversed()) {
                val city = citiesList[i]
                if (city.id == null) {
                    db?.personDao?.insert(city)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                cityQuery = query
                initLoaderManager(LOADER_ID)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun updateCityInfo(city: City) {
        val temperature = Utils().convertTemperatureType(city.temperature, tempType)
        val tempString = "${temperature.roundToInt()} °"
        cityName!!.text = city.name
        cityTemp!!.text = tempString
    }

    fun initLoaderManager(loaderId: Int) {
        val loaderManager = supportLoaderManager
        loaderManager.restartLoader(loaderId, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<City?> {
        return CityLoader(this, cityQuery)
    }

    override fun onLoadFinished(loader: Loader<City?>, data: City?) {
        if (data != null) {
            citiesList.add(0, data)
            adapter!!.notifyDataSetChanged()
            updateCityInfo(data)
        } else {
            Toast.makeText(this,
                    "City not found: $cityQuery",
                    Toast.LENGTH_LONG).show()
        }
        supportLoaderManager.destroyLoader(LOADER_ID)
    }

    override fun onLoaderReset(loader: Loader<City?>) {
        citiesList.clear()
        adapter!!.notifyDataSetChanged()
    }

    companion object {
        const val LOADER_ID = 1
    }
}