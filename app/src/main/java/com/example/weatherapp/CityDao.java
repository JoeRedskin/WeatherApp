package com.example.weatherapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface CityDao {

    // Добавление City в бд
    @Insert
    void insertAll(City... cities);

    // Удаление City из бд
    @Delete
    void delete(City city);

    @Query("DELETE FROM city")
    void deleteAll();

    // Получение всех City из бд
    @Query("SELECT * FROM city ORDER BY id DESC")
    List<City> getAllCities();

    // Получение всех City из бд с условием
    @Query("SELECT * FROM city WHERE temperatureType LIKE :type")
    List<City> getByTemperatureType(String type);

    @Query("INSERT INTO city VALUES (:id, :name, :temperatureType, :temperature, :date) ")
    @TypeConverters({DateConverter.class})
    void setCity(Integer id,String name, String temperatureType, Double temperature, LocalDateTime date);

}
