package com.example.kazdreamweather.cities.data.repository

import com.example.kazdreamweather.cities.data.local.CitiesService
import com.example.kazdreamweather.cities.domain.model.City
import com.example.kazdreamweather.cities.domain.repository.CitiesRepository

class CitiesRepositoryImpl(
    private val citiesService: CitiesService
): CitiesRepository {

    override fun getAllCities(): List<City> {
        return citiesService.cities
    }

    override fun insertCity(city: City) {
        citiesService.cities.let { cities ->
            if (!cities.contains(city)) {
                cities.map { it.isSelected = false }
                cities.add(city)
            } else {
                cities.map { it.isSelected = false }
                cities.find { it.name == city.name }?.isSelected = true
            }
        }
    }

    override fun deleteCity(city: City) {
        if (citiesService.cities.contains(city)) citiesService.cities.remove(city)
    }

}