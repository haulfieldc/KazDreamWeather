package com.example.kazdreamweather.android.presentation.cities.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kazdreamweather.android.R
import com.example.kazdreamweather.android.presentation.DeepBlue

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Checkbox(
    checked: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        if (checked) Color.Green else DeepBlue
    )
    Box(
        modifier
            .size(24.dp)
            .background(backgroundColor, CircleShape)
    ) {
        AnimatedVisibility(
            visible = checked,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Icon(
                painter = painterResource(R.drawable.icons_system_check),
                contentDescription = null,
                tint = DeepBlue
            )
        }
    }
}