package com.mkao.skysync.data.repositories

import com.mkao.skysync.data.city.CityDao
import com.mkao.skysync.data.forecast.ForecastDao
import javax.inject.Inject

class ForecastRepository @Inject constructor(private val forecastDao: ForecastDao) : IForecastRepository {

}