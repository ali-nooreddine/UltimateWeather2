package com.sachimi.ultweather.data.model.converters

import androidx.room.TypeConverter
import com.sachimi.ultweather.data.model.Main
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Ali Noureddine on 2020-01-25.
 */
class MainConverter {
    @TypeConverter
    fun fromMainToJson(main: Main): String {
        return Gson().toJson(main)
    }

    @TypeConverter
    fun fromJsonToMain(jsonMain: String): Main {
        val mainType = object : TypeToken<Main>() {}.type
        return Gson().fromJson<Main>(jsonMain, mainType)
    }
}