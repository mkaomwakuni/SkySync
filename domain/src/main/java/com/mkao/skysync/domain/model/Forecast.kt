package com.mkao.skysync.domain.model

data class Forecast(
    var city: String,
    var dailyForecasts: List<DailyForecasts> = listOf()
)

data class DailyForecasts(
    val timeStamps: String = "",
    val hourlyForecasts: List<HourlyForecasts> = listOf(),
    val temp: Int = 0,
    val minTemp: Int = 0,
    val maxTemp: Int = 0,
    val windSpeed: Int = 0,
    val humidity: Int = 0,
    val dewPoint: Int = 0,
    val weather: Weather = Weather.Sunny
)

data class HourlyForecasts(
    val timeStamps: String,
    val temp: Int,
    val weather: Weather
)

enum class Weather{
    Sunny,
    Cloudy,
    Rainy,
    Snowy,
    Stormy,
    Windy,
    Foggy,
    Hail,
    Thunderstorm
}