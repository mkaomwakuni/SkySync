package com.mkao.skysync.domain.usecases.cities

import com.mkao.skysync.domain.base.FlowUseCase
import com.mkao.skysync.domain.repositories.cities.ICityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchCities @Inject constructor(
    private val citiesRepository: ICityRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<String, List<String>>(dispatcher) {

    override fun excute(parameters: String): Flow<Result<List<String>>> {
        return flow {
            emit(Result.Loading)
            emit(Result.Success(citiesRepository.getCities(parameters)))
        }
    }

}