package com.example.weatherapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<City> {

    ArrayList<City> citiesList = new ArrayList<>();
    CityAdapter adapter;
    RecyclerView recyclerView;
    TextView cityName;
    TextView cityTemp;
    String cityQuery;
    String tempType = "C";
    AppDatabase db;
    private RecyclerView.LayoutManager layoutManager;
    public static final Integer LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.cityName);
        cityTemp = findViewById(R.id.cityTemperature);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CityAdapter(this, citiesList);
        recyclerView.setAdapter(adapter);

        Switch sw = findViewById(R.id.switchTemperature);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!citiesList.isEmpty()) {
                    City.convertTemperatureType(citiesList.get(0));
                    updateCityInfo(citiesList.get(0));
                }
                tempType = isChecked ? "C" : "F";
            }
        });

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "populus-database").
                allowMainThreadQueries().
                fallbackToDestructiveMigration().
                build();
        //db.getPersonDao().deleteAll();
        List<City> everyone = db.getPersonDao().getAllCities();
        if (!everyone.isEmpty()) {
            citiesList.addAll(everyone);
            adapter.notifyDataSetChanged();
            updateCityInfo(citiesList.get(0));
        }

        //initLoaderManager(LOADER_ID);
//        initCities();
//        updateCityInfo(citiesList.get(0));
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (!citiesList.isEmpty()) {
            for (int i = citiesList.size() - 1; i >= 0; i--) {
                City city = citiesList.get(i);
                if (city.getId() == null) {
                    db.getPersonDao().setCity(city.getId(),
                            city.getName(),
                            city.getTemperatureType(),
                            city.getTemperature(),
                            city.getDate());
                }
            }
        }

        //getSupportLoaderManager().destroyLoader(LOADER_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cityQuery = query;
                initLoaderManager(LOADER_ID);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void updateCityInfo(City city) {
        String tempString = (int) Math.round(city.getTemperature()) + "°";
        cityName.setText(city.getName());
        cityTemp.setText(tempString);
    }
//
//    public void initCities(){
//        citiesList.add(new City("Stuttgart", "C", 12.3));
//        citiesList.add(new City("Munchen", "C", -23.0));
//        citiesList.add(new City("Koln", "C", 21.0));
//        adapter.notifyDataSetChanged();
//    }

    public void initLoaderManager(int loaderId) {
//        LoaderManager loaderManager = getLoaderManager();
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.restartLoader(loaderId, null, this);
    }


    @NonNull
    @Override
    public Loader<City> onCreateLoader(int i, Bundle bundle) {
        return new CityLoader(this, cityQuery, tempType);
    }



    @Override
    public void onLoadFinished(@NonNull androidx.loader.content.Loader<City> loader, City data) {
        //citiesList.clear();

        if (data != null) {
            citiesList.add(0, data);
            adapter.notifyDataSetChanged();
            updateCityInfo(data);
        } else {
            Toast.makeText(this,
                    "City not found: " + cityQuery,
                    Toast.LENGTH_LONG).show();
        }
        getSupportLoaderManager().destroyLoader(LOADER_ID);
    }

    @Override
    public void onLoaderReset(@NonNull androidx.loader.content.Loader<City> loader) {
        citiesList.clear();
        adapter.notifyDataSetChanged();
    }
}