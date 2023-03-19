package com.example.kazdreamweather.di

import com.example.kazdreamweather.cities.data.local.CitiesService
import com.example.kazdreamweather.cities.data.repository.CitiesRepositoryImpl
import com.example.kazdreamweather.cities.domain.repository.CitiesRepository
import com.example.kazdreamweather.cities.domain.usecase.DeleteCityUseCase
import com.example.kazdreamweather.cities.domain.usecase.GetAllCitiesUseCase
import com.example.kazdreamweather.cities.domain.usecase.InsertCityUseCase
import com.example.kazdreamweather.weather.data.remote.WeatherService
import com.example.kazdreamweather.weather.data.remote.RemoteDataSource
import com.example.kazdreamweather.weather.data.repository.WeatherRepositoryImpl
import com.example.kazdreamweather.weather.domain.repository.WeatherRepository
import com.example.kazdreamweather.weather.usecase.GetWeatherDataUseCase
import com.example.kazdreamweather.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { WeatherService() }
    factory { CitiesService() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<CitiesRepository> { CitiesRepositoryImpl(get()) }
    factory { GetWeatherDataUseCase() }
    factory { GetAllCitiesUseCase() }
    factory { InsertCityUseCase() }
    factory { DeleteCityUseCase() }
}

private val sharedModules = listOf(domainModule, dataModule, utilityModule)

fun getSharedModules() = sharedModules












