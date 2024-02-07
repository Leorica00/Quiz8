package com.example.quiz8.presentation.screen.places

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz8.domain.usecase.GetPlacesUseCase
import com.example.quiz8.domain.util.Resource
import com.example.quiz8.presentation.mapper.toPresentation
import com.example.quiz8.presentation.state.PlacesState
import com.example.quiz8.presentation.util.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(private val getPlacesUseCase: GetPlacesUseCase): ViewModel() {

    private val _placesStateFlow = MutableStateFlow(PlacesState())
    val placesStateFlow = _placesStateFlow.asStateFlow()

    init {
        getPlaces()
    }

    private fun getPlaces() {
        viewModelScope.launch {
            getPlacesUseCase().collect {resource ->
                when(resource) {
                    is Resource.Loading -> {
                        d("successData", "loading")
                        _placesStateFlow.update { currentState -> currentState.copy(isLoading = resource.loading) }
                    }
                    is Resource.Success -> {
                        _placesStateFlow.update { currentState -> currentState.copy(places = resource.response.map { it.toPresentation() }) }
                        d("successData", _placesStateFlow.value.places.toString())
                    }
                    is Resource.Error -> {
                        d("successData", resource.throwable.toString())
                        updateErrorMessage(getErrorMessage(resource.error))
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(errorMessage: Int?) {
        _placesStateFlow.update { currentState ->
            currentState.copy(errorMessage = errorMessage)
        }
    }

}