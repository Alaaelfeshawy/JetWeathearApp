package com.example.jetweatherapp.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MenuItem(title:String , icon : ImageVector ,  onClick : ()->Unit) {
    DropdownMenuItem(
        text = { Text(modifier = Modifier.padding(4.dp), text = title,) },
        onClick = { onClick.invoke() },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = Color.LightGray,
            )
        }
    )
}