package com.example.kazdreamweather.weather.domain.repository

import com.example.kazdreamweather.weather.domain.model.WeatherData
import com.example.kazdreamweather.util.Resource

internal interface WeatherRepository {

    suspend fun getWeatherData(cityName: String): Resource<WeatherData>

}