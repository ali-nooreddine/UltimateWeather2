package com.sachimi.ultweather.data.model.converters

import androidx.room.TypeConverter
import com.sachimi.ultweather.data.model.Coord
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Ali Noureddine on 2020-01-25.
 */
class CoordTypeConverter {

    @TypeConverter
    fun fromJsonToCoord(jsonCoord: String): Coord {
        val coordType = object : TypeToken<Coord>() {}.type
        return Gson().fromJson<Coord>(jsonCoord, coordType)
    }

    @TypeConverter
    fun fromCoordToJson(coord: Coord): String {
        return Gson().toJson(coord)
    }


}