package com.mkao.skysync.domain.usecases.forecast

import com.mkao.skysync.domain.base.FlowUseCase
import com.mkao.skysync.domain.base.Result
import com.mkao.skysync.domain.model.Forecast
import com.mkao.skysync.domain.repositories.forecast.IForecastRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchForecast @Inject constructor(
    private val forecastRepository: IForecastRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<String,Forecast>(dispatcher) {


    override fun excute(parameters: String): Flow<Result<Forecast>> {
        return flow {
            emit(Result.Loading)
            emit(Result.Success(forecastRepository.getForecast(parameters)))
        }
    }
}