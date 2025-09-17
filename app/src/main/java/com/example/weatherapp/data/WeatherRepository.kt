package com.example.weatherapp.data

class WeatherRepository {
    private val api = ApiClient.api
    suspend fun getByCity(city: String): CurrentWeatherResponse = api.getCurrentByCity(city)
    suspend fun getByCoords(lat: Double, lon: Double): CurrentWeatherResponse = api.getCurrentByCoords(lat, lon)
}
