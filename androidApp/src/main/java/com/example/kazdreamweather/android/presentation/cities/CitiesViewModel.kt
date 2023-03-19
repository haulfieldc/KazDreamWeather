package com.example.kazdreamweather.android.presentation.cities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kazdreamweather.cities.domain.model.City
import com.example.kazdreamweather.cities.domain.usecase.GetAllCitiesUseCase
import com.example.kazdreamweather.cities.domain.usecase.InsertCityUseCase

class CitiesViewModel(
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val insertCityUseCase: InsertCityUseCase
): ViewModel() {

    var citiesState by mutableStateOf(getAllCitiesUseCase())
        private set

    fun onCityClicked(city: City) {
        insertCityUseCase(city)
    }

    fun addNewCity(cityName: String) {
        val newCity = City(
            name = cityName,
            isSelected = true
        )
        insertCityUseCase(newCity)
    }

}