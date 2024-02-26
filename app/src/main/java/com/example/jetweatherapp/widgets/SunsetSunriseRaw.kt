package com.example.jetweatherapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetweatherapp.R
import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.utils.formatDateTime

@Preview
@Composable
fun SunsetSunriseRaw(weatherData: Weather?=null){
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp),
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "humidity")
            Text(
                modifier = Modifier.padding(2.dp),
                text = weatherData?.list?.first()?.sunset?.let { formatDateTime(it) }.toString() )
        }
        Row {
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp),
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Pressure")
            Text(
                modifier = Modifier.padding(2.dp),
                text = weatherData?.list?.first()?.sunrise?.let { formatDateTime(it) }.toString()
            )
        }
    }
}