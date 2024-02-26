package com.example.jetweatherapp.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetweatherapp.R
import com.example.jetweatherapp.components.AppBar
import com.example.jetweatherapp.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AboutScreen(navController: NavHostController ?= null) {
    Scaffold(
        topBar = {
            AppBar(appBarTitle = "About" , navController = navController,onButtonClicked = {
                navController?.popBackStack()
            })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.about_app),
                style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
            )
            Text(
                text = stringResource(id = R.string.api_used),
                 style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Light),
                 textAlign = TextAlign.Center,
                 modifier = Modifier.padding(horizontal = 12.dp)
                )
        }
    }


}