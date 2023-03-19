package com.example.kazdreamweather.weather.data.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class WeatherInfoDto(

    @SerialName("clouds")
    val clouds: Clouds,

    @SerialName("dt")
    val datetime: Int,

    @SerialName("dt_txt")
    val dateTimeText: String,

    @SerialName("main")
    val main: Main,

    @SerialName("pop")
    val pop: Double,

    @SerialName("snow")
    val snow: Snow? = null,

    @SerialName("sys")
    val sys: Sys,

    @SerialName("visibility")
    val visibility: Int,

    @SerialName("weather")
    val weather: List<Weather>,

    @SerialName("wind")
    val wind: Wind
)

@kotlinx.serialization.Serializable
data class Clouds(
    @SerialName("all")
    val all: Int
)

@kotlinx.serialization.Serializable
data class Main(

    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)

@kotlinx.serialization.Serializable
data class Snow(
    @SerialName("3h")
    val `3h`: Double? = null
)

@kotlinx.serialization.Serializable
data class Sys(
    val pod: String
)

@kotlinx.serialization.Serializable
data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)



