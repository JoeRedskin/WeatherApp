package com.example.weatherapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;

@Entity
public class City {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name = "";
    private String temperatureType;
    private Double temperature;

    @TypeConverters({DateConverter.class})
    private LocalDateTime date;

    public City( String name, String temperatureType, Double temperature){
        this.name = name;
        this.temperatureType = temperatureType;
        this.temperature = temperature;
        this.date = LocalDateTime.now();
    }

    public static void convertTemperatureType(City city){
        switch (city.temperatureType){
            case "F":
                city.temperatureType = "C";
                city.temperature = (city.temperature-32) / 1.8;
                break;
            case "C":
                city.temperatureType = "F";
                city.temperature = city.temperature * 1.8 + 32 ;
                break;
            default:
                break;
        }
    }

    public String getName() {
        return name;
    }

    public String getTemperatureType() {
        return temperatureType;
    }

    public Double getTemperature() {
        return temperature;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
