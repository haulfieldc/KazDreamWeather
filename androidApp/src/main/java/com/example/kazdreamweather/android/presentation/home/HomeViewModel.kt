package com.example.kazdreamweather.android.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kazdreamweather.cities.domain.model.City
import com.example.kazdreamweather.cities.domain.usecase.GetAllCitiesUseCase
import com.example.kazdreamweather.util.Lg
import com.example.kazdreamweather.util.Resource
import com.example.kazdreamweather.weather.data.model.Weather
import com.example.kazdreamweather.weather.domain.model.CityState
import com.example.kazdreamweather.weather.domain.model.PrimaryInfo
import com.example.kazdreamweather.weather.domain.model.WeatherData
import com.example.kazdreamweather.weather.usecase.GetWeatherDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val getWeatherDataUseCase: GetWeatherDataUseCase
): ViewModel() {

    private val _citiesList = MutableStateFlow(getAllCitiesUseCase())
    val citiesList: StateFlow<List<City>> = _citiesList.asStateFlow()

    private val _weatherDataState = MutableStateFlow<WeatherData?>(null)
    val weatherDataState: StateFlow<WeatherData?> = _weatherDataState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getCurrentWeatherState() {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = getWeatherDataUseCase(
                getCurrentSelectedCity()
            )) {
                is Resource.Success -> {
                    result.data?.let {
                        _weatherDataState.value = it
                    }
                    _isLoading.value = false
                }
                is Resource.Error -> {

                }
            }
        }
    }

    private fun getCurrentSelectedCity(): String {
        return _citiesList.value.find { it.isSelected }?.name ?: ""
    }

}