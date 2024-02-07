package com.example.quiz8.domain.usecase

import com.example.quiz8.domain.repository.PlacesRepository
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(private val placesRepository: PlacesRepository) {
    suspend operator fun invoke() = placesRepository.getPlaces()
}