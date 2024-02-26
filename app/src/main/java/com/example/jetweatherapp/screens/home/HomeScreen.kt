package com.example.jetweatherapp.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetweatherapp.data.DataOrException
import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.utils.formatDate
import com.example.jetweatherapp.utils.formatDecimals
import com.example.jetweatherapp.components.AppBar
import com.example.jetweatherapp.widgets.HumidityWindPressureRaw
import com.example.jetweatherapp.widgets.SunsetSunriseRaw
import com.example.jetweatherapp.widgets.WeatherDayItem
import com.example.jetweatherapp.widgets.WeatherImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel, searchQuery: String?) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(initialValue = DataOrException(
        loading = true
    ) ){
        value = viewModel.getWeatherData(searchQuery ?: "cairo")
    }.value

    val isMoreClicked = remember {
        mutableStateOf(false)
    }

    if (weatherData.loading == true){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            CircularProgressIndicator()
        }
    }else if (!weatherData.e?.message.isNullOrBlank()){
        Toast.makeText(LocalContext.current ,weatherData.e?.message , Toast.LENGTH_LONG ).show()
    }else if (weatherData.data != null){
        Scaffold(
            topBar = {
                AppBar(
                    appBarTitle = "${weatherData.data?.city?.name}, ${weatherData.data?.city?.country}" ,
                    navController = navController,
                    isMoreClicked = isMoreClicked,
                    isMainScreen = true,
                    borderStroke = 2.dp,
                    borderColor = Color.LightGray
                )
            }

        ) {
            Column(Modifier.padding(it)) {
                HomeContent(navController = navController , weatherData.data)
            }
        }

    }else{
        //todo handle empty view
    }

}

@Composable
fun HomeContent(navController: NavHostController, weatherData: Weather?) {

    val weatherItem = weatherData?.list?.get(0)
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem?.weather?.get(0)?.icon}.png"

    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = weatherData?.list?.first()?.dt?.let { formatDate(it) } ?:"",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier.padding(6.dp)
            )

        Surface(
            modifier = Modifier
                .size(200.dp)
                .padding(4.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
          Column(
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
          ) {
              WeatherImage(imageUrl)
              Text(text = (weatherData?.list?.get(0)?.temp?.day?.let { formatDecimals(it) } + "º"),
                  fontSize = 24.sp,
                  fontWeight = FontWeight.ExtraBold
              )
              Text(text = weatherData?.list?.get(0)?.weather?.get(0)?.main ?: "", fontStyle = FontStyle.Italic)

          }
        }

        HumidityWindPressureRaw(weatherData)
        Divider()
        SunsetSunriseRaw(weatherData)
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "This Week", style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ))
        LazyColumn(Modifier.padding(12.dp)){
            items(weatherData?.list ?: emptyList()){item->
                WeatherDayItem(item)
            }

        }
    }
}




