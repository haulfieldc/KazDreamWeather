package com.example.kazdreamweather.android.presentation.navigation

sealed class Screen(val route: String) {

    object HomeScreen: Screen("main_screen")
    object ForecastsScreen: Screen("forecasts_screen")
    object CitiesScreen: Screen("cities_screen")
    object AddCityScreen: Screen("add_city_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}
