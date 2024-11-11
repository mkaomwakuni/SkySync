package com.mkao.skysync.presentation.forecast.events

import com.mkao.skysync.domain.model.DailyForecasts
import com.mkao.skysync.presentation.forecast.state.ViewStatus
import com.mkao.skysync.presentation.forecast.state.ViewType
import com.mkao.skysync.presentation.forecast.state.WeatherUnit

sealed class ForecastViewEvent {
    data class GetForecast(val city: String): ForecastViewEvent()
    data class SetSelectedDailyForecast(val selectedDailyForecast: DailyForecasts): ForecastViewEvent()
    data class SetViewStatus(val viewStatus: ViewStatus) : ForecastViewEvent()
    data class SetViewType(val viewType: ViewType) : ForecastViewEvent()
    data class SetWeatherUnit(val weatherUnit: WeatherUnit) : ForecastViewEvent()
}