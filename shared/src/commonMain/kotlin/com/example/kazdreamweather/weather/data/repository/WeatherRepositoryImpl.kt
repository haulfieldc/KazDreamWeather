package com.example.kazdreamweather.weather.data.repository

import com.example.kazdreamweather.util.Lg
import com.example.kazdreamweather.weather.data.remote.RemoteDataSource
import com.example.kazdreamweather.weather.data.util.toWeatherData
import com.example.kazdreamweather.weather.domain.model.WeatherData
import com.example.kazdreamweather.weather.domain.repository.WeatherRepository
import com.example.kazdreamweather.util.Resource

internal class WeatherRepositoryImpl(
    private val remoteDateSource: RemoteDataSource
): WeatherRepository {


    override suspend fun getWeatherData(cityName: String): Resource<WeatherData> {
        return try {
            Lg.l.d { "WeatherRepositoryImpl: ${remoteDateSource.getWeatherData(cityName).toWeatherData()}" }
            Resource.Success(
                data = remoteDateSource.getWeatherData(cityName).toWeatherData()
            )
        } catch(e: Exception) {
            val asd = e.printStackTrace().toString()
            Lg.l.d { "e: ${e.stackTraceToString()}" }
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}