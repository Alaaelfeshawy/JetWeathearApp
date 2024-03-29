package com.example.jetweatherapp.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetweatherapp.R
import com.example.jetweatherapp.navigation.Route
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

   val scale = remember {
      Animatable(0f)
   }
   LaunchedEffect(key1 = true){
      scale.animateTo(0.9f , animationSpec = tween(
         durationMillis = 800,
         easing = {
            OvershootInterpolator(8f).getInterpolation(it)
         }
      ))
      delay(2000L)
      navController.popBackStack()
      navController.navigate(Route.HOME.name)
   }

   Surface(
      modifier = Modifier.size(330.dp).scale(scale = scale.value),
      color = Color.White,
      border = BorderStroke(2.dp , color = Color.LightGray),
      shape = CircleShape,

   ) {
      Column(
         modifier = Modifier.padding(1.dp).fillMaxSize(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         Image(
            modifier = Modifier.size(96.dp),
            painter = painterResource(id = R.drawable.sun),
            contentDescription = "sun" ,
            contentScale = ContentScale.Fit
         )
         Text(
            text = "Find the sun?",
            style = TextStyle(
               color = Color.LightGray,
               fontSize = 26.sp,
            )
            )
      }
   }
}