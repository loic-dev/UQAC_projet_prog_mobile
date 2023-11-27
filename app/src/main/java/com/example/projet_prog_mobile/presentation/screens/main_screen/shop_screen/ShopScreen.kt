package com.example.projet_prog_mobile.presentation.screens.main_screen.shop_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.projet_prog_mobile.R

@Composable
fun ShopScreen(modifier: Modifier) {
    Column(modifier=modifier) {
        Text(text = "SHOPPING",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.light_black)
            ))
    }
}