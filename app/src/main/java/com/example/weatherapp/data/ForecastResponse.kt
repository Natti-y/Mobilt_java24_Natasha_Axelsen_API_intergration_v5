package com.example.weatherapp.data

data class ForecastResponse(
    val list: List<WeatherEntry>,
    val city: City
)

data class WeatherEntry(
    val dt_txt: String,
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val humidity: Int
)

data class Weather(
    val description: String,
    val icon: String
)

data class City(
    val name: String,
    val country: String
)
