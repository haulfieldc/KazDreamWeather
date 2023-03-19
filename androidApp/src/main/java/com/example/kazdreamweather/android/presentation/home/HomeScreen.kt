package com.example.kazdreamweather.android.presentation.home

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.kazdreamweather.android.presentation.DarkBlue
import com.example.kazdreamweather.android.presentation.DeepBlue
import com.example.kazdreamweather.android.presentation.components.CityBar
import com.example.kazdreamweather.android.presentation.navigation.Screen
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kazdreamweather.android.presentation.home.components.WeatherTablet

@Composable
fun HomeScreen(navController: NavController) {

    val homeViewModel: HomeViewModel = koinViewModel()
    val citiesListState by homeViewModel.citiesList.collectAsState()
    val currentWeatherState by homeViewModel.weatherDataState.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()
    val errorState by homeViewModel.isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
        ) {
            CityBar(
                state = citiesListState,
                backgroundColor = DeepBlue,
                onBarClicked = {
                    navController.navigate(Screen.CitiesScreen.route)
                }
            )
            if (!isLoading) {
                currentWeatherState?.let {
                    if (!isLoading) {
                        WeatherTablet(
                            state = it.primaryInfo,
                            backgroundColor = DeepBlue,
                        )
                        ForecastButton(navController = navController, it.details.cityName)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
            }
        }
    }
    LaunchedEffect(citiesListState) {
        homeViewModel.getCurrentWeatherState()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ForecastButton(navController: NavController, cityName: String) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .height(56.dp)
        ,
        onClick = {
            navController.navigate(route = Screen.ForecastsScreen.withArgs(cityName))
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                maxLines = 1,
                text = "Show forecasts",
                color = DeepBlue,
                textAlign = TextAlign.Center
            )
        }
    }
}