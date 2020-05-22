package com.sachimi.ultweather.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sachimi.ultweather.data.model.converters.*

@Entity(tableName = "current_weather")
data class CurrentWeatherResponse @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    @TypeConverters(CoordTypeConverter::class)
    val coord: Coord,
    @TypeConverters(WeatherListConverter::class)
    val weather: List<Weather>,
    val base: String,
    @TypeConverters(MainConverter::class)
    val main: Main,
    val visibility: Int,
    @TypeConverters(WindConverter::class)
    val wind: Wind,
    @TypeConverters(CloudsConverter::class)
    val clouds: Clouds,
    val dt: Int,
    @TypeConverters(SysConverter::class)
    val sys: Sys,
    val timezone: Int,
    @Ignore
    val cod: Int = 0
)