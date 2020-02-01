package com.ali.ultimateweather.data.provider

import android.content.Context
import com.ali.ultimateweather.internal.UnitSystem

/**
 * Created by Ali Noureddine on 2020-01-17.
 */

const val UNIT_SYSTEM = "UNIT_SYSTEM"

class UnitProvider(context: Context) : PreferenceProvider(context) {

    fun getUnitProvider(): UnitSystem {
        val selectedName = preferences.getString(UNIT_SYSTEM, UnitSystem.METRIC.name)
        return UnitSystem.valueOf(selectedName!!)
    }
}