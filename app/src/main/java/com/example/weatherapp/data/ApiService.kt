package com.example.weatherapp.data

import com.example.weatherapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentByCity(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "sv",
        @Query("appid") apiKey: String = BuildConfig.OPENWEATHER_API_KEY
    ): CurrentWeatherResponse

    @GET("data/2.5/weather")
    suspend fun getCurrentByCoords(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "sv",
        @Query("appid") apiKey: String = BuildConfig.OPENWEATHER_API_KEY
    ): CurrentWeatherResponse
}
