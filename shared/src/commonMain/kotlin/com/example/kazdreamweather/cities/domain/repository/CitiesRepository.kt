package com.example.kazdreamweather.cities.domain.repository

import com.example.kazdreamweather.cities.domain.model.City

interface CitiesRepository {

    fun getAllCities(): List<City>

    fun insertCity(city: City)

    fun deleteCity(city: City)

}