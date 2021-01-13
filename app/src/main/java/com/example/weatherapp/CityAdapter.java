package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<City> citiesList;

    public CityAdapter(Context context, ArrayList<City> citiesList){
        this.context = context;
        this.citiesList = citiesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        City city = citiesList.get(position);
        String information = city.getName() + ", " +
                (int) Math.round(city.getTemperature()) +
                "°" + city.getTemperatureType();
        String date = formatDate(city.getDate());

        holder.cityInformation.setText(information);
        holder.cityDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    private String formatDate(LocalDateTime dateObject) {
        DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("dd.MM.yyyy H:mm:ss");
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy H:mm:ss");
        return formatter.format(dateObject);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cityInformation;
        TextView cityDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cityInformation = itemView.findViewById(R.id.cityInformation);
            this.cityDate = itemView.findViewById(R.id.cityDate);
        }
    }
}
