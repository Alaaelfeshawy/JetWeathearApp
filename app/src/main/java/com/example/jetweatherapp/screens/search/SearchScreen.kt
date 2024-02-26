package com.example.jetweatherapp.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetweatherapp.components.AppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    val searchQuery = rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val validate = remember(searchQuery.value) {
        searchQuery.value.trim().isNotEmpty()
    }
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            AppBar(appBarTitle = "Search" , onButtonClicked = {
                navController.popBackStack()
            })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White),
            ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(84.dp)
                    .padding(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.LightGray

                ),
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                },
                shape = RoundedCornerShape(15.dp),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (validate){
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("SearchQuery", searchQuery.value)
                            navController.popBackStack()
                            keyboardController?.hide()
                        }

                    }
                ),
                singleLine = true,
                placeholder = { Text(text = "Search")},
                )
        }

    }

}