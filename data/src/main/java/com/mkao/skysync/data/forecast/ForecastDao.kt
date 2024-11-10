package com.mkao.skysync.data.forecast

import com.mkao.skysync.domain.model.Forecast

interface ForecastDao {
    suspend fun generateForecast(): Forecast
}