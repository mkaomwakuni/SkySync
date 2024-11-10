package com.mkao.skysync.data.di

import com.mkao.skysync.data.repositories.CityRepository
import com.mkao.skysync.data.repositories.ForecastRepository
import com.mkao.skysync.domain.repositories.cities.ICityRepository
import com.mkao.skysync.domain.repositories.forecast.IForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindForecastRepository(
        forecastRepository: ForecastRepository
    ): IForecastRepository

    @Binds
    @Singleton
    fun bindCityRepository(
        cityRepository: CityRepository
    ): ICityRepository
}