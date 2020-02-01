package com.ali.ultimateweather.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ali.ultimateweather.data.db.WeatherDatabase
import com.ali.ultimateweather.data.model.CurrentWeatherResponse
import com.ali.ultimateweather.data.network.ConnectivityInterceptor
import com.ali.ultimateweather.data.network.OpenWeatherMapService
import com.ali.ultimateweather.internal.NoConnectivityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Ali Noureddine on 2020-01-25.
 */

class WeatherRepository(
    private val context: Context
) {

    private val currentWeatherResponse = MutableLiveData<CurrentWeatherResponse>()
    private val currentWeatherDao = WeatherDatabase.getInstance(context).currentWeatherDao()

    suspend fun getCurrentWeatherByName(cityName: String): LiveData<CurrentWeatherResponse> {
        return withContext(Dispatchers.IO) {
            getWeatherData(cityName)
            return@withContext currentWeatherDao.getCurrentWeatherByName(cityName).also {
                currentWeatherResponse.postValue(it.value)
            }
        }
    }

    private suspend fun getWeatherData(cityName: String) {
        try {
            val response = OpenWeatherMapService(ConnectivityInterceptor(context)).getCurrentWeatherByCityName(cityName).await()
            currentWeatherResponse.postValue(response)
            currentWeatherDao.insert(response)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Connectivity Exception")
        }
    }

    companion object {

        @Volatile
        private var instance: WeatherRepository? = null

        operator fun invoke(context: Context): WeatherRepository {
            return instance ?: synchronized(this) {
                instance ?: WeatherRepository(context).also { instance = it }
            }
        }
    }

}