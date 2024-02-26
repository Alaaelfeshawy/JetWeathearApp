package com.example.jetweatherapp.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun WeatherImage(imageUrl: String , size : Dp = 80.dp) {
    Icon(painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "imageIcon",
        modifier = Modifier.size(size),
        )
}

