package com.example.jetweatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetweatherapp.screens.about.AboutScreen
import com.example.jetweatherapp.screens.favorite.FavoriteScreen
import com.example.jetweatherapp.screens.favorite.FavoriteViewModel
import com.example.jetweatherapp.screens.home.HomeScreen
import com.example.jetweatherapp.screens.home.HomeViewModel
import com.example.jetweatherapp.screens.search.SearchScreen
import com.example.jetweatherapp.screens.settings.SettingsScreen
import com.example.jetweatherapp.screens.settings.SettingsViewModel
import com.example.jetweatherapp.screens.splash.SplashScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = Route.SPLASH.name){
        composable(route =  Route.SPLASH.name){
            SplashScreen(navController)
        }
        composable(route =  Route.HOME.name){
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val searchQuery = it.savedStateHandle.get<String?>("SearchQuery")
            HomeScreen(navController,homeViewModel,searchQuery)
        }
        composable(route =  Route.SEARCH.name){
            SearchScreen(navController)
        }
        composable(route =  Route.FAVORITE.name){
            val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
            FavoriteScreen(navController , favoriteViewModel)
        }
        composable(route =  Route.ABOUT.name){
            AboutScreen(navController)
        }
        composable(route =  Route.SETTINGS.name){
            val settingsViewModel = hiltViewModel<SettingsViewModel>()
            SettingsScreen(navController , settingsViewModel)
        }
    }

}