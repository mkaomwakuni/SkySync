package com.mkao.skysync.presentation.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkao.skysync.domain.base.Result.Loading.successOr
import com.mkao.skysync.domain.model.DailyForecasts
import com.mkao.skysync.domain.model.Forecast
import com.mkao.skysync.domain.usecases.cities.AddCity
import com.mkao.skysync.domain.usecases.cities.FetchCities
import com.mkao.skysync.domain.usecases.forecast.FetchForecast
import com.mkao.skysync.presentation.forecast.events.ForecastViewEvent
import com.mkao.skysync.presentation.forecast.events.LocationViewEvent
import com.mkao.skysync.presentation.forecast.state.ForecastState
import com.mkao.skysync.presentation.forecast.state.LocationState
import com.mkao.skysync.presentation.forecast.state.ViewStatus
import com.mkao.skysync.presentation.utils.AnimationDuration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val fetchForecast: FetchForecast,
    private val  fetchCities: FetchCities,
    private val  addCity: AddCity
): ViewModel() {
    private val  _forecastViewState = MutableStateFlow(ForecastState())
    val forecastViewState: StateFlow<ForecastState> = _forecastViewState

    private val  _locationViewState = MutableStateFlow(LocationState())
    val locationViewState : StateFlow<LocationState> = _locationViewState

    fun onForecastEvent(event: ForecastViewEvent) = viewModelScope.launch {
        when(event) {
            is ForecastViewEvent.GetForecast -> {
                fetchForecast(event.city).collectLatest { result ->
                    val forecast = result.successOr(Forecast())
                    _forecastViewState.emit(
                        forecastViewState.value.copy(
                            forecast = forecast,
                            selectedDailyForecast = forecast.dailyForecasts.firstOrNull()?: DailyForecasts(),
                            viewStatus = ViewStatus.Running
                        )
                    )
                }
            }
            is ForecastViewEvent.SetSelectedDailyForecast ->{
                _forecastViewState.emit(
                    forecastViewState.value.copy(
                        selectedDailyForecast = event.selectedDailyForecast
                    )
                )
            }
            is ForecastViewEvent.SetViewStatus -> {
                _forecastViewState.emit(
                    forecastViewState.value.copy(
                        viewStatus = event.viewStatus
                    )
                )

                if (event.viewStatus == ViewStatus.HandlingErrors){
                    delay(AnimationDuration * 2L)
                    _forecastViewState.emit(
                        forecastViewState.value.copy(
                            viewStatus = ViewStatus.Idle
                        )
                    )
                }
            }
            is ForecastViewEvent.SetViewType -> {
                _forecastViewState.emit(
                    forecastViewState.value.copy(
                        viewType = event.viewType
                    )
                )
            }
            is ForecastViewEvent.SetWeatherUnit -> {
                _forecastViewState.emit(
                    forecastViewState.value.copy(
                        weatherUnit = event.weatherUnit
                    )
                )
            }
        }
    }

    fun onLocationEvent(event: LocationViewEvent) = viewModelScope.launch {
        when(event) {
            is LocationViewEvent.SetLocation -> {
                onForecastEvent(ForecastViewEvent.GetForecast(event.location))
                addCity(event.location)
                fetchCities(event.location).collectLatest { cities ->
                    _locationViewState.emit(
                        locationViewState.value.copy(
                            query = event.location,
                            cities = cities.successOr(emptyList()),
                            errorGettingLocation = false,
                            errorGettingPermissions = false
                        )
                    )
                }
            }

            is LocationViewEvent.SearchCities -> {
                if (event.query.isEmpty()){
                    onForecastEvent(ForecastViewEvent.SetViewStatus(ViewStatus.Idle))
                }

                fetchCities(event.query).collectLatest { cities ->
                    _locationViewState.emit(
                        locationViewState.value.copy(
                            query = event.query,
                            cities = cities.successOr(emptyList()),
                            errorGettingLocation = false,
                            errorGettingPermissions = false
                        )
                    )
                }
            }
            LocationViewEvent.LocationError -> {
                onForecastEvent(ForecastViewEvent.SetViewStatus(ViewStatus.HandlingErrors))
                _locationViewState.emit(
                    locationViewState.value.copy(
                        errorGettingLocation = true
                    )
                )
            }
            LocationViewEvent.PermissionError -> {
                onForecastEvent(ForecastViewEvent.SetViewStatus(ViewStatus.HandlingErrors))
                _locationViewState.emit(
                    locationViewState.value.copy(
                        errorGettingPermissions = true
                    )
                )
            }
        }
    }
}