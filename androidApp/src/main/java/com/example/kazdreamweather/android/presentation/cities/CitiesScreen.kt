package com.example.kazdreamweather.android.presentation.cities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kazdreamweather.android.R
import com.example.kazdreamweather.android.presentation.DarkBlue
import com.example.kazdreamweather.android.presentation.DeepBlue
import com.example.kazdreamweather.android.presentation.navigation.Screen
import com.example.kazdreamweather.cities.domain.model.City
import org.koin.androidx.compose.koinViewModel

@Composable
fun CitiesScreen(navController: NavController) {

    val citiesViewModel: CitiesViewModel = koinViewModel()

    Column(
        Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Header(
            onCloseClick = { navController.popBackStack() }
        )
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(citiesViewModel.citiesState) { city ->
                CityComponent(
                    city = city,
                    onCitySelect = {
                        citiesViewModel.onCityClicked(city)
                        navController.popBackStack() } ,
                )
            }
        }
        AddCityComponent(navController)
    }
}

@Composable
fun CityComponent(
    city: City,
    onCitySelect: (City) -> Unit
) {
    Card(
        backgroundColor = DeepBlue,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(),
                    onClick = { onCitySelect.invoke(city) },
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    maxLines = 1,
                    text = city.name,
                    color = Color.White
                )
                com.example.kazdreamweather.android.presentation.cities.components.Checkbox(checked = city.isSelected)
            }
        }
    }
}

@Composable
private fun Header(onCloseClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onCloseClick
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_back_whity),
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Cities",
            color = Color.White
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddCityComponent(navController: NavController) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        onClick = {
            navController.navigate(Screen.AddCityScreen.route)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                maxLines = 1,
                text = "Add new city",
                color = DeepBlue,
                textAlign = TextAlign.Center
            )
        }
    }
}