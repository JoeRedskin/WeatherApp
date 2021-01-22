package com.example.weatherapp

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

@Entity
data class City(
        @Expose
        var name: String,
        @Embedded
        @Expose
        val main: Main,
        @SerializedName("dt")
        @Expose
        var date: Long,
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null) {

    data class Main(
            @Expose
            val temp: Double
    )

    fun getInfo(name: String, temp: Double) : String{
        return "$name, ${temp.roundToInt()} °"
    }
}