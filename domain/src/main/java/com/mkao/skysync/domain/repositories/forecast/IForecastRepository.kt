package com.mkao.skysync.domain.repositories.forecast

import com.mkao.skysync.domain.model.Forecast

interface IForecastRepository {
    suspend fun getForecast(city: String): Forecast
}