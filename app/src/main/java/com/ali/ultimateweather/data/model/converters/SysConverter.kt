package com.ali.ultimateweather.data.model.converters

import androidx.room.TypeConverter
import com.ali.ultimateweather.data.model.Sys
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Ali Noureddine on 2020-01-25.
 */
class SysConverter {
    @TypeConverter
    fun fromJsonToSys(jsonSys: String): Sys {
        val sysType = object : TypeToken<Sys>() {}.type
        return Gson().fromJson<Sys>(jsonSys, sysType)
    }

    @TypeConverter
    fun fromSysToJson(sys: Sys): String {
        return Gson().toJson(sys)
    }


}