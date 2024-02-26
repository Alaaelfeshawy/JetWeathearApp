package com.example.jetweatherapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetweatherapp.navigation.Route

@Composable
fun Menu(
    isMoreClicked: MutableState<Boolean>,
    navController: NavHostController?=null
) {
    DropdownMenu(
        modifier = Modifier.background(color = Color.White),
        expanded = isMoreClicked.value,
        onDismissRequest = { isMoreClicked.value = false }) {
        Column(Modifier.padding(8.dp)) {
            MenuItem(title = "Favorite" , icon = Icons.Default.FavoriteBorder){
                isMoreClicked.value = false
                navController?.navigate(Route.FAVORITE.name)
            }
            MenuItem(title = "Settings" , icon = Icons.Default.Settings){
                isMoreClicked.value = false
                navController?.navigate(Route.SETTINGS.name)
            }
            MenuItem(title = "About" , icon = Icons.Default.Info){
                isMoreClicked.value = false
                navController?.navigate(Route.ABOUT.name)
            }
        }
    }
}
