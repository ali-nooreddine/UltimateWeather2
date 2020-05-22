package com.sachimi.ultweather.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sachimi.ultweather.data.model.CurrentWeatherResponse

/**
 * Created by Ali Noureddine on 2020-01-25.
 */
@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currentWeatherResponse: CurrentWeatherResponse)

    @Query("select * from current_weather where id =:id")
     fun getCurrentWeather(id: Long): LiveData<CurrentWeatherResponse>

    @Query("select * from current_weather where name like '%' || :cityName || '%'")
     fun getCurrentWeatherByName(cityName: String): LiveData<CurrentWeatherResponse>

//    @Delete
//    fun deleteCurrentWeather(id: Long)
}