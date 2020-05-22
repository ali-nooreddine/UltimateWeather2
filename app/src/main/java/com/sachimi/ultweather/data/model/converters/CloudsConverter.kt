package com.sachimi.ultweather.data.model.converters

import androidx.room.TypeConverter
import com.sachimi.ultweather.data.model.Clouds
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Ali Noureddine on 2020-01-25.
 */
class CloudsConverter {
    @TypeConverter
    fun fromCouldToJson(clouds: Clouds): String {
        return Gson().toJson(clouds)
    }

    @TypeConverter
    fun fromJsonToCloud(jsonCloud: String): Clouds {
        val cloudType = object : TypeToken<Clouds>() {}.type
        return Gson().fromJson<Clouds>(jsonCloud, cloudType)
    }
}