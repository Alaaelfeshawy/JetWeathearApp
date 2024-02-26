package com.example.jetweatherapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetweatherapp.model.WeatherItem
import com.example.jetweatherapp.utils.formatDate
import com.example.jetweatherapp.utils.formatDecimals

@Preview
@Composable
fun WeatherDayItem(weatherItem: WeatherItem?=null) {
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem?.weather?.get(0)?.icon}.png"

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = Color.White,
        shape = RoundedCornerShape(topStartPercent = 30 , bottomEndPercent = 30),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = weatherItem?.dt?.let { formatDate(it) }?.split(",")?.get(0) ?: "")
            WeatherImage(imageUrl = imageUrl , size = 40.dp)
            Surface(
                shape = RoundedCornerShape(15.dp),
                color = Color(0xFFFFC400)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = weatherItem?.weather?.get(0)?.description ?: "" ,
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(SpanStyle(
                    color = Color.Blue
                )){
                    append(weatherItem?.temp?.max?.let { formatDecimals(it) } + "º")
                }
                withStyle(SpanStyle(
                    color = Color.LightGray
                )){
                    append(weatherItem?.temp?.min?.let { formatDecimals(it) } + "º")
                }
            })

        }
    }
}