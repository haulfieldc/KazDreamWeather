package com.example.kazdreamweather.weather.usecase

import com.example.kazdreamweather.util.Resource
import com.example.kazdreamweather.weather.domain.model.WeatherData
import com.example.kazdreamweather.weather.domain.repository.WeatherRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetWeatherDataUseCase: KoinComponent {
    private val repository: WeatherRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(cityName: String): Resource<WeatherData> {
        return repository.getWeatherData(cityName)
    }

}