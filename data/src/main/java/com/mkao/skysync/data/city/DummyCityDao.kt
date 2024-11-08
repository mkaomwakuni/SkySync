package com.mkao.skysync.data.city

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DummyCityDao : CityDao {

    private val cities = mutableListOf(
        "London",
        "Paris",
        "New York",
        "Tokyo",
        "Sydney",
        "Rio de Janeiro",
        "Cairo",
        "Dubai",
        "Mumbai",
        "Beijing",
        "Moscow",
        "Buenos Aires",
        "Sao Paulo",
        "Toronto",
        "Berlin",
        "Bangkok",
        "Singapore",
        "Hong Kong",
        "Seoul",
        "Barcelona",
        "Madrid",
        "Rome",
        "Vienna",
        "Amsterdam",
        "Prague",
        "Nairobi",
        "Chicago",
        "Mombasa",
        "Kilifi",
        "Lagos",
        "Johannesburg",
        "Dar es Salaam",
        "Khartoum",
        "Casablanca",
        "Pretoria",
    )

    override suspend fun getCities(): Flow<List<City>> {
        return flowOf(cities)
    }


    override suspend fun insertCity(city: String) {
        if (!cities.contains(city)){
            cities.add(city)
        }
    }
}