package com.example.quiz8.presentation.state

import com.example.quiz8.presentation.model.Place

data class PlacesState(
    val isLoading: Boolean = false,
    val places: List<Place>? = null,
    val errorMessage: Int? = null
)