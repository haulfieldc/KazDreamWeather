package com.example.kazdreamweather.cities.domain.usecase

import com.example.kazdreamweather.cities.domain.model.City
import com.example.kazdreamweather.cities.domain.repository.CitiesRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetAllCitiesUseCase: KoinComponent {

    private val repository: CitiesRepository by inject()

    operator fun invoke(): List<City> = repository.getAllCities()
}