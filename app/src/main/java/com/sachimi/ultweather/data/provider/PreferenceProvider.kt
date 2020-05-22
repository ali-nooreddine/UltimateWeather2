package com.sachimi.ultweather.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * Created by Ali Noureddine on 2020-01-17.
 */
abstract class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext

    protected val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

}