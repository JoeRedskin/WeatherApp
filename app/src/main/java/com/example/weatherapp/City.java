package com.example.weatherapp;

import java.util.Date;

public class City {

    private String name;
    private String temperatureType;
    private Double temperature;
    private Date date;

    public City(String name, String temperatureType, Double temperature){
        this.name = name;
        this.temperatureType = temperatureType;
        this.temperature = temperature;
        this.date = new Date();
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

    public Date getDate() {
        return date;
    }
}
