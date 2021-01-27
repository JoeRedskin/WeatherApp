package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.model.City

@Dao
interface CityDao {
    // Добавление City в бд
    @Insert
    suspend fun insert(city: City)

    // Удаление City из бд
    @Delete
    suspend fun delete(city: City)

    // Удаление всех данных из бд
    @Query("DELETE FROM city")
    suspend fun deleteAll()

    // Получение всех City из бд
    @get:Query("SELECT * FROM city ORDER BY id DESC")
    val getCities: LiveData<List<City>>
}