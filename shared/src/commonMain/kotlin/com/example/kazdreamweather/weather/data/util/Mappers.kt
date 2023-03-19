package com.example.kazdreamweather.weather.data.util

import com.example.kazdreamweather.weather.data.model.WeatherDataDto
import com.example.kazdreamweather.weather.domain.model.Details
import com.example.kazdreamweather.weather.domain.model.Forecast
import com.example.kazdreamweather.weather.domain.model.PrimaryInfo
import com.example.kazdreamweather.weather.domain.model.WeatherData
import kotlin.math.round

internal fun WeatherDataDto.toWeatherData(): WeatherData {
    val today = this.list.first()
    val forecasts = mutableListOf<Forecast>()
    this.list.forEach { dtoCast ->
        forecasts.add(
            Forecast(
                datetime = dtoCast.dateTimeText,
                temperature = dtoCast.main.temp.toCelcius(),
                clouds = dtoCast.clouds.all,
                visibility = dtoCast.visibility,
                windSpeed = dtoCast.wind.speed,
                windDegree = dtoCast.wind.deg,
                description = dtoCast.weather.first().description,
                icon = getImageUrl(dtoCast.weather.first().icon)
            )
        )
    }
    return WeatherData(
        primaryInfo = PrimaryInfo(
            currentDate = today.dateTimeText,
            cityName = this.city.name,
            currentTemperature = today.main.temp.toCelcius(),
            icon = getImageUrl(today.weather.first().icon),
            pressure = today.main.pressure,
            humidity = today.main.humidity,
            windSpeed = today.wind.speed,
            weatherDesc = today.weather.first().description
        ),
        details = Details(
            country = this.city.country,
            cityName = this.city.name,
            population = this.city.population,
            sunrise = this.city.sunrise,
            sunset = this.city.sunset,
            timezone = this.city.timezone
        ),
        forecast = forecasts
    )
}

private fun getImageUrl(weatherImage: String) =
    "https://openweathermap.org/img/wn/$weatherImage${".png"}"

private fun Double.toCelcius(): Double {
    val celcius = this - 273.15
    return celcius.rounded(1)
}

fun Double.rounded(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

