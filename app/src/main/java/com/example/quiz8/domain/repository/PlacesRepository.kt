package com.example.quiz8.domain.repository

import com.example.quiz8.domain.model.GetPlace
import com.example.quiz8.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    suspend fun getPlaces(): Flow<Resource<List<GetPlace>>>
}