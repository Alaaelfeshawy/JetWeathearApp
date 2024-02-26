package com.example.jetweatherapp.screens.favorite

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetweatherapp.components.AppBar
import com.example.jetweatherapp.model.Favorite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavHostController, favoriteViewModel: FavoriteViewModel) {
   Scaffold(
       topBar = {
           AppBar(appBarTitle = "Favorite" , navController = navController,onButtonClicked = {
               navController.popBackStack()
           })
       }
   ) {
       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(it),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Top
       ) {
           val favList = favoriteViewModel.favList.collectAsState()
           LazyColumn{
               items(favList.value){
                   CityRaw(favorite = it, favoriteViewModel= favoriteViewModel , navController = navController)
               }
           }
       }
   }

}

@Composable
private fun CityRaw(
    favorite: Favorite,
    navController : NavHostController,
    favoriteViewModel: FavoriteViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("SearchQuery", favorite.city)
                navController.popBackStack()
            },
        color = Color(0xFFB2DFDB),
        shape = CircleShape.copy(topEnd = CornerSize(0.dp)),
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = favorite.city)
            Surface(
                shape = CircleShape,
                color = Color(0xFFD1E3E1),
            ) {
                Text(text = favorite.country, modifier = Modifier.padding(4.dp))
            }
            Icon(
                modifier = Modifier.clickable {
                    favoriteViewModel.deleteFavorite(favorite = favorite)
                },
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Red.copy(0.3f)
            )
        }
    }
}