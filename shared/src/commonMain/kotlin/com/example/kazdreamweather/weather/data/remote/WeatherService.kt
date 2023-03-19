package com.example.kazdreamweather.weather.data.remote

import com.example.kazdreamweather.weather.data.model.WeatherDataDto
import io.ktor.client.call.*
import io.ktor.client.request.*

internal class WeatherService: KtorApi() {

    suspend fun getWeatherData(city: String): WeatherDataDto = client.get {
        pathUrl("data/2.5/forecast/")
        parameter("q", city)
    }.body()

}