package com.ali.ultimateweather.data.network

import com.ali.ultimateweather.BuildConfig
import com.ali.ultimateweather.data.model.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ali Noureddine on 2020-01-16.
 */
const val API_KEY = BuildConfig.WEATHER_API_KEY

interface OpenWeatherMapService {
    //    https://api.openweathermap.org/data/2.5/weather?q=beirut&units=metric
    @GET("weather")
    fun getCurrentWeatherByCityName(@Query("q") cityName: String, @Query("units") unit: String = "metric")
            : Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): OpenWeatherMapService {

            val requestInterceptor = Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-key", API_KEY)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(OpenWeatherMapService::class.java)

        }
    }
}