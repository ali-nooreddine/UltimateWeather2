package com.ali.ultimateweather.ui.weather.info

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ali.ultimateweather.data.provider.UnitProvider
import com.ali.ultimateweather.data.repository.WeatherRepository
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
 * Created by Ali Noureddine on 2020-01-17.
 */
class WeatherInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val appContext = application.applicationContext

    private val unitSystem = UnitProvider(appContext).getUnitProvider()

    private val repository = WeatherRepository(appContext)

    val selectedUnit: String
        get() = unitSystem.name

    val weather by lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            repository.getCurrentWeatherByName("Beirut")
        }
    }
}