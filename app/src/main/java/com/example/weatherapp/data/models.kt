package com.example.weatherapp.data

data class CurrentWeatherResponse(
    val name: String,
    val weather: List<WeatherItem>,
    val main: MainInfo,
    val wind: Wind?
)

data class WeatherItem(val description: String, val icon: String)
data class MainInfo(val temp: Double, val humidity: Int)
data class Wind(val speed: Double)
