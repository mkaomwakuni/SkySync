package com.mkao.skysync.data.di

import com.mkao.skysync.data.city.CityDao
import com.mkao.skysync.data.city.DummyCityDao
import com.mkao.skysync.data.forecast.DummyForecastDao
import com.mkao.skysync.data.forecast.ForecastDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun provideCityDao(): CityDao = DummyCityDao()

    @Provides
    @Singleton
    fun provideForecastDao(): ForecastDao = DummyForecastDao()
}