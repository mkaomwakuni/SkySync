package com.mkao.skysync.data.city

import kotlinx.coroutines.flow.Flow

interface CityDao {
    suspend fun getCities(): Flow<List<City>>
    suspend fun insertCity(city: String)
}