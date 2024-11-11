package com.mkao.skysync.presentation.forecast.events

sealed class LocationViewEvent {
    data class SearchCities(val query: String) : LocationViewEvent()
    data class SetLocation(val location: String) : LocationViewEvent()
    object LocationError: LocationViewEvent()
    object PermissionError: LocationViewEvent()
}