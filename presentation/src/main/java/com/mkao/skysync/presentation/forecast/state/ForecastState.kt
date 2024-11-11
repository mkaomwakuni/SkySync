package com.mkao.skysync.presentation.forecast.state

import com.mkao.skysync.domain.model.DailyForecasts
import com.mkao.skysync.domain.model.Forecast

// Data class representing the state of a forecast
data class ForecastState (
    val forecast: Forecast = Forecast(),
    val selectedDailyForecast: DailyForecasts = DailyForecasts(),
    val viewType: ViewType = ViewType.Simple,
    val viewStatus: ViewStatus = ViewStatus.Idle,
    val weatherUnit: WeatherUnit = WeatherUnit.Metric
)

// Enum class to define various statuses of the view
enum class ViewStatus {
    Idle,
    Loading,
    Running,
    HandlingErrors
}

// Enum class to define the types of views available
enum class ViewType {
    Simple,
    Detailed
}

// Enum class to define the units for weather measurements
enum class WeatherUnit (val indication: String) {
    Metric("ºC"),
    Imperial("ºF")
}