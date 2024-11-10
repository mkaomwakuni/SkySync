package com.mkao.skysync.domain.usecases.cities

import com.mkao.skysync.domain.base.FlowUseCase
import com.mkao.skysync.domain.repositories.cities.ICityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCity @Inject constructor(
    private  val citiesRepository: ICityRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<String, Unit>(dispatcher){


    override fun excute(parameters: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

}