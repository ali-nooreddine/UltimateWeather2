package com.ali.ultimateweather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ali.ultimateweather.data.model.CurrentWeatherResponse
import com.ali.ultimateweather.data.model.converters.*

/**
 * Created by Ali Noureddine on 2020-01-25.
 */

@Database(entities = [CurrentWeatherResponse::class], version = 1)
@TypeConverters(value = [CloudsConverter::class, CoordTypeConverter::class, MainConverter::class,
    SysConverter::class, WeatherListConverter::class, WindConverter::class])
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao


    companion object {
        @Volatile
        private var instance: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(context, WeatherDatabase::class.java, "weather.db").build()
        }
    }


}