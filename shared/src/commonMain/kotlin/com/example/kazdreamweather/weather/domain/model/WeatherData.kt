package com.example.kazdreamweather.weather.domain.model

@kotlinx.serialization.Serializable
data class WeatherData(
    val primaryInfo: PrimaryInfo,
    val details: Details,
    val forecast: List<Forecast>
)

@kotlinx.serialization.Serializable
data class PrimaryInfo(
    val currentDate: String,
    val cityName: String,
    val currentTemperature: Double,
    val icon: String,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val weatherDesc: String
)

@kotlinx.serialization.Serializable
data class Details(
    val country: String,
    val cityName: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int,
)

@kotlinx.serialization.Serializable
data class Forecast(
    val datetime: String,
    val temperature: Double,
    val clouds: Int,
    val visibility: Int,
    val windSpeed: Double,
    val windDegree: Int,
    val description: String,
    val icon: String,
)

enum class dayTimes {
    Morning, Noon, Evening, Midnight
}


