package com.example.kazdreamweather.android.presentation.forecasts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.kazdreamweather.android.R
import com.example.kazdreamweather.android.presentation.DeepBlue
import com.example.kazdreamweather.android.util.formatToDay
import com.example.kazdreamweather.android.util.formatToHours
import com.example.kazdreamweather.weather.domain.model.Forecast
import com.example.kazdreamweather.weather.domain.model.WeatherData
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastsScreen(cityName: String) {

    val forecastsViewModel: ForecastsViewModel = koinViewModel()
    val currentWeatherState by forecastsViewModel.weatherDataState.collectAsState()
    val isLoading by forecastsViewModel.isLoading.collectAsState()

    forecastsViewModel.getCurrentWeatherState(cityName)

    currentWeatherState.let { data ->
        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                backgroundColor = DeepBlue,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!isLoading) {
                        Text(
                            text = "City: ${data?.details?.cityName}",
                            fontSize = 32.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Country: ${data?.details?.country}",
                            fontSize = 24.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = rememberAsyncImagePainter(data?.primaryInfo?.icon),
                            contentDescription = null,
                            modifier = Modifier.width(200.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "${data?.primaryInfo?.currentTemperature}°C",
                            fontSize = 42.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = data?.primaryInfo?.weatherDesc ?: "",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            AdditionalInfo(
                                title = "Population:",
                                value = data?.details?.population.toString(),
                                icon = ImageVector.vectorResource(id = R.drawable.ic_profile_friend),
                                iconTint = Color.White,
                            )
                        }
                        Spacer(modifier = Modifier.height(54.dp))
                        currentWeatherState?.let { WeatherForecast(it) }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator(
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AdditionalInfo(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    iconTint: Color = Color.White,
    textColor: Color = Color.White
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(25.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            color = textColor,
            fontSize = 16.sp
        )
        Text(
            text = value,
            color = textColor,
            fontSize = 16.sp
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecast(
    state: WeatherData,
    modifier: Modifier = Modifier
) {
    state.forecast.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "5-days forecast with 3-hours step",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(content = {
                items(data) { weatherData ->
                    HourlyWeatherDisplay(
                        forecast = weatherData,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
            })
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherDisplay(
    forecast: Forecast,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
    val formattedTime = remember(forecast) {
        forecast.datetime
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime.formatToDay(),
            color = Color.LightGray
        )
        Text(
            text = formattedTime.formatToHours(),
            color = Color.LightGray
        )
        Image(
            painter = rememberAsyncImagePainter(forecast.icon),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = "${forecast.temperature}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}