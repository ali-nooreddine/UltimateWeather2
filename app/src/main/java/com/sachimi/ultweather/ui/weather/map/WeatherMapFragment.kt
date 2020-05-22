package com.sachimi.ultweather.ui.weather.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sachimi.ultweather.R

/**
 * Created by Ali Noureddine on 2020-01-16.
 */
class WeatherMapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_map_fragment, container, false)
    }
}