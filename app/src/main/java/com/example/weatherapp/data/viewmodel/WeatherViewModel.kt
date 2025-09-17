package com.example.weatherapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.CurrentWeatherResponse
import com.example.weatherapp.data.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val data: CurrentWeatherResponse) : UiState()
    data class Error(val message: String) : UiState()
}

class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Idle)
    val state: StateFlow<UiState> = _state

    fun loadWeatherByCity(city: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val response = repo.getByCity(city)
                _state.value = UiState.Success(response)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Okänt fel")
            }
        }
    }

    fun loadWeatherByCoords(lat: Double, lon: Double) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val response = repo.getByCoords(lat, lon)
                _state.value = UiState.Success(response)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Okänt fel")
            }
        }
    }
}
