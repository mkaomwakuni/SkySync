package com.mkao.skysync.data.repositories

import com.mkao.skysync.data.city.CityDao
import javax.inject.Inject

class CityRepository @Inject constructor(private val cityDao: CityDao): ICityRepository {

}