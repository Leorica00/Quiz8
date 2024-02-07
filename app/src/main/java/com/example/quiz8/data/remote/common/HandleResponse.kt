package com.example.quiz8.data.remote.common

import com.example.taskn21.domain.util.HandleErrorStates
import com.example.quiz8.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

class HandleResponse {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = call()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Resource.Success(response = body))
            } else {
                throw HttpException(response)
            }
        } catch (t: Throwable) {
            emit(Resource.Error(error = HandleErrorStates.handleException(t), throwable = t))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}