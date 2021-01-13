package com.example.weatherapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.time.LocalDateTime

@Dao
interface CityDao {
    // Добавление City в бд
    @Insert
    fun insertAll(vararg cities: City?)

    // Добавление City в бд
    @Insert
    fun insert(city: City?)

    // Удаление City из бд
    @Delete
    fun delete(city: City?)

    @Query("DELETE FROM city")
    fun deleteAll()

    // Получение всех City из бд
    @get:Query("SELECT * FROM city ORDER BY id DESC")
    val allCities: List<City>

    @Query("INSERT INTO city VALUES (:id, :name, :temperature, :date) ")
    fun setCity(id: Int?, name: String?, temperature: Double?, date: LocalDateTime?)
}