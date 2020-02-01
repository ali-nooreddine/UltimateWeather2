package com.ali.ultimateweather.data.model.converters

import androidx.room.TypeConverter
import com.ali.ultimateweather.data.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Ali Noureddine on 2020-01-25.
 */
class WeatherListConverter {

    @TypeConverter
    fun fromWeatherListToJson(weather: List<Weather>): String {
        return Gson().toJson(weather)
    }

    @TypeConverter
    fun fromJsonToWeatherList(jsonWeatherList: String): List<Weather> {
        val weatherListType = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(jsonWeatherList, weatherListType)
    }
}