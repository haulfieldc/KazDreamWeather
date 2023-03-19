package com.example.kazdreamweather.weather.data.model

@kotlinx.serialization.Serializable
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)