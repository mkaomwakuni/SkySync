package com.mkao.skysync.domain.repositories.cities

interface ICityRepository {
    suspend fun getCities(query: String): List<String>
    suspend fun addCity(city:String)
}