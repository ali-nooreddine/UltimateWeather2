package com.sachimi.ultweather.ui.weather.info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sachimi.ultweather.R
import com.sachimi.ultweather.data.network.ConnectivityInterceptor
import com.sachimi.ultweather.data.network.OpenWeatherMapService
import com.sachimi.ultweather.internal.NoConnectivityException
import com.sachimi.ultweather.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.weather_info_fragment.*
import kotlinx.coroutines.launch

/**
 * Created by Ali Noureddine on 2020-01-16.
 */
class WeatherInfoFragment : ScopedFragment() {

    private lateinit var viewModel: WeatherInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(WeatherInfoViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(viewLifecycleOwner, Observer {
            if (it == null)
                return@Observer
            textView.text = it.toString()

        })
    }

    private suspend fun fetchWeather(unit: String) {
        try {
            val fetchedCurrentWeather =
                OpenWeatherMapService(ConnectivityInterceptor(context!!)).getCurrentWeatherByCityName("beirut", unit).await()
            println("Unit of measure is: ${unit}")
            println("the result is: ${fetchedCurrentWeather.name} ${fetchedCurrentWeather.main.temp}")

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connection.", e)
        }
    }
}