package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    // Добавление City в бд
    @Insert
    fun insert(city: City)

    // Удаление City из бд
    @Delete
    fun delete(city: City)

    // Удаление всех данных из бд
    @Query("DELETE FROM city")
    fun deleteAll()

    // Получение всех City из бд
    @get:Query("SELECT * FROM city ORDER BY id DESC")
    val getCities: LiveData<List<City>>

}