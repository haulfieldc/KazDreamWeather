package com.example.kazdreamweather.android.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.kazdreamweather.weather.domain.model.PrimaryInfo
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherTablet(
    state: PrimaryInfo,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    state.let { data ->
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${
                        SimpleDateFormat("dd MMMM").format(Date())
                    }",
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = rememberAsyncImagePainter(data.icon),
                    contentDescription = null,
                    modifier = Modifier.width(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.currentTemperature}°C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.toInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = com.example.kazdreamweather.android.R.drawable.ic_pressure),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.humidity.toInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(id = com.example.kazdreamweather.android.R.drawable.ic_drop),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.toInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(id = com.example.kazdreamweather.android.R.drawable.ic_wind),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherDataDisplay(
    value: Int,
    unit: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    iconTint: Color = Color.White
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(25.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$value$unit",
            style = textStyle
        )
    }
}