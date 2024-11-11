package com.mkao.skysync.presentation.forecast.state

data class LocationState (
    val query: String = "",
    val cities: List<String> = emptyList(),
    val errorGettingLocation: Boolean = false,
    val errorGettingPermissions: Boolean = false
)