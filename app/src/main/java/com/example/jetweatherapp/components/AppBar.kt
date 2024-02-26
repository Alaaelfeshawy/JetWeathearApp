package com.example.jetweatherapp.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetweatherapp.model.Favorite
import com.example.jetweatherapp.navigation.Route
import com.example.jetweatherapp.screens.favorite.FavoriteViewModel
import com.example.jetweatherapp.widgets.Menu

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar(
    appBarTitle : String?,
    navController: NavHostController?=null,
    isMoreClicked: MutableState<Boolean> = mutableStateOf(false),
    isMainScreen: Boolean = false,
    borderStroke: Dp = 0.dp,
    borderColor : Color = Color.Transparent,
    favoriteViewModel: FavoriteViewModel = hiltViewModel<FavoriteViewModel>(),
    onButtonClicked: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(8.dp)
            .border(borderStroke, color = borderColor),
        title = { Text(text = appBarTitle.toString() , style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        ) },
        navigationIcon = {
            if (!isMainScreen){
                Icon(
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back arrow",
                    )
            }else{
                val isFavorite = favoriteViewModel.favList.collectAsState().value.find {
                    it.city == appBarTitle?.split(",")?.get(0)
                }
                val favorite = Favorite(
                    city = appBarTitle?.split(",")?.get(0) ?: "",
                    country = appBarTitle?.split(",")?.get(1) ?: "",
                )
                IconButton(onClick = {
                    if (isFavorite?.city?.isNotBlank() == true){
                        favoriteViewModel.deleteFavorite(favorite)
                    }else{
                        favoriteViewModel.insertFavorite(favorite)
                    }
                }) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = if (isFavorite?.city?.isNotBlank() == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorite",
                        tint = if (isFavorite?.city?.isNotBlank() == true) Color.Red else Color.LightGray
                    )
                }

            }
        },
        actions = {
            if (isMainScreen){
                Icon(
                    modifier = Modifier.clickable {
                        navController?.navigate(Route.SEARCH.name)
                    },
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
                Icon(
                    modifier = Modifier.clickable {
                        isMoreClicked.value = true
                    },
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            }else{
                Box{}
            }
            if (isMoreClicked.value) Menu(isMoreClicked, navController) else Box{}
        },
    )
}