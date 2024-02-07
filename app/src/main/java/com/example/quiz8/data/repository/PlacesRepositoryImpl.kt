package com.example.quiz8.data.repository

import com.example.quiz8.data.remote.common.HandleResponse
import com.example.quiz8.data.remote.mapper.toDomain
import com.example.quiz8.data.remote.service.PlacesApiService
import com.example.quiz8.domain.model.GetPlace
import com.example.quiz8.domain.repository.PlacesRepository
import com.example.quiz8.domain.util.Resource
import com.example.taskn21.data.remote.mapper.base.asResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val placesApiService: PlacesApiService,
    private val handleResponse: HandleResponse
) : PlacesRepository {
    override suspend fun getPlaces(): Flow<Resource<List<GetPlace>>> = handleResponse.safeApiCall {
            placesApiService.getPlaces()
        }.asResource {
            it.map { it.toDomain() }
        }
}
