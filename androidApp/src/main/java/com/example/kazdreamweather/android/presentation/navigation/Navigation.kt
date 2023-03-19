package com.example.kazdreamweather.android.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kazdreamweather.android.presentation.cities.components.AddCityDialog
import com.example.kazdreamweather.android.presentation.cities.CitiesScreen
import com.example.kazdreamweather.android.presentation.forecasts.ForecastsScreen
import com.example.kazdreamweather.android.presentation.home.HomeScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.CitiesScreen.route,
        ) {
            CitiesScreen(navController)
        }
        composable(route = Screen.AddCityScreen.route) {
            AddCityDialog(onDismiss = { navController.popBackStack() })
        }
        composable(
            route = Screen.ForecastsScreen.route + "/{cityName}",
            arguments = listOf(
                navArgument("cityName") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            entry.arguments?.getString("cityName")?.let { ForecastsScreen(cityName = it) }
        }
    }
}


