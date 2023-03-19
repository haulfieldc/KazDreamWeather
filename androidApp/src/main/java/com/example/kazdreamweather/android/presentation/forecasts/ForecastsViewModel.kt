package com.example.kazdreamweather.android.presentation.forecasts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kazdreamweather.util.Resource
import com.example.kazdreamweather.weather.domain.model.WeatherData
import com.example.kazdreamweather.weather.usecase.GetWeatherDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForecastsViewModel(
    private val getWeatherDataUseCase: GetWeatherDataUseCase
): ViewModel() {

    private val _weatherDataState = MutableStateFlow<WeatherData?>(null)
    val weatherDataState: StateFlow<WeatherData?> = _weatherDataState.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getCurrentWeatherState(city: String) {
        viewModelScope.launch {
            when (val result = getWeatherDataUseCase(city)) {
                is Resource.Success -> {
                    result.data?.let {
                        if (_weatherDataState.value == it) {
                            Log.d("qwe", "not set")
                        } else {
                            _weatherDataState.value = it
                            Log.d("qwe", "set")
                        }
                        _isLoading.value = false
                    }
                }
                is Resource.Error -> {}
            }
        }
    }

}