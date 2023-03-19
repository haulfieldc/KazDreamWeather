package com.example.kazdreamweather.weather.data.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class WeatherDataDto(

    val cod: String,
    val message: Int,
    val cnt: Int,

    @SerialName("city")
    val city: WeatherCity,

    @SerialName("list")
    val list: List<WeatherInfoDto>

)

@kotlinx.serialization.Serializable
data class WeatherCity(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)

@kotlinx.serialization.Serializable
data class Coord(
    val lat: Double,
    val lon: Double
)