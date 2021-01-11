package com.example.weatherapp;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;
import java.util.ArrayList;

public class CityLoader extends AsyncTaskLoader<City> {
    String cityQuery;
    String tempType;

    public CityLoader(Context context, String cityQuery, String tempType) {
            super(context);
            this.cityQuery = cityQuery;
            this.tempType = tempType;
        }

    @Override
    protected void onStartLoading() {
            forceLoad();
        }

    @Override
    public City loadInBackground() {
        if (cityQuery == null) return null;
        return DataQuery.fetchCityData(cityQuery, tempType);
    }
}
