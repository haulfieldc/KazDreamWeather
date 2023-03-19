package com.example.kazdreamweather.cities.data.local

import com.example.kazdreamweather.cities.domain.model.City

class CitiesService {

    val cities = mutableListOf(
        City(name = "Astana", isSelected = true),
        City(name = "Almaty", isSelected = false)
    )

}