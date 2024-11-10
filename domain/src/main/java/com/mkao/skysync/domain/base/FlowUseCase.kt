package com.mkao.skysync.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase <in P, R>(private val coroutineDispatcher: CoroutineDispatcher){
    operator fun invoke(parameters: P): Flow<Result<R>> {
        return excute(parameters)
            .catch { e -> emit(Result.Error(Exception(e)))
            }.flowOn(coroutineDispatcher)
    }
    abstract fun excute(parameters: P): Flow<Result<R>>
}