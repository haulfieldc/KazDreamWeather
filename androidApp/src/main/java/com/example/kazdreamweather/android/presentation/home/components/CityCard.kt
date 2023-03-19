package com.example.kazdreamweather.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kazdreamweather.android.R
import com.example.kazdreamweather.cities.domain.model.City

@Composable
fun CityBar(
    state: List<City>,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onBarClicked: () -> Unit
) {
    state.let { data ->
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onBarClicked() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Current city: ${data.find { it.isSelected }?.name}",
                    color = Color.White
                )
                Image(
                    painter = painterResource(R.drawable.ic_arrow_down_small),
                    contentDescription = null,
                )
            }
        }
    }
}