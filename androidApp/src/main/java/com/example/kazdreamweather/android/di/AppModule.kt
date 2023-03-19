package com.example.kazdreamweather.android.di

import com.example.kazdreamweather.android.presentation.cities.CitiesViewModel
import com.example.kazdreamweather.android.presentation.forecasts.ForecastsViewModel
import com.example.kazdreamweather.android.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel(get(), get()) }
    viewModel { CitiesViewModel(get(), get()) }
    viewModel { ForecastsViewModel(get()) }

}