package com.example.kazdreamweather.weather.data.remote

import com.example.kazdreamweather.util.Dispatcher
import com.example.kazdreamweather.util.Lg
import kotlinx.coroutines.withContext

internal class RemoteDataSource(
    private val apiService: WeatherService,
    private val dispatcher: Dispatcher
) {

    suspend fun getWeatherData(city: String) = withContext(dispatcher.io) {
        apiService.getWeatherData(city)
    }

}