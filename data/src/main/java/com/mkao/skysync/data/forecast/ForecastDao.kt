package com.mkao.skysync.data.forecast

interface ForecastDao {
    suspend fun generateForecast(): Forecast
}