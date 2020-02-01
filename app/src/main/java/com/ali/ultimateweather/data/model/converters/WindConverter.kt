package com.ali.ultimateweather.data.model.converters

import androidx.room.TypeConverter
import com.ali.ultimateweather.data.model.Wind
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Ali Noureddine on 2020-01-25.
 */
class WindConverter {
    @TypeConverter
    fun fromWindToJson(wind: Wind): String {
        return Gson().toJson(wind)
    }

    @TypeConverter
    fun fromJsonToWind(jsonWind: String): Wind {
        val windType = object : TypeToken<Wind>() {}.type
        return Gson().fromJson<Wind>(jsonWind, windType)
    }
}