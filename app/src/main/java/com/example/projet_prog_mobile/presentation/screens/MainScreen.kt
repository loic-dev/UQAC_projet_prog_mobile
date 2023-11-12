package com.example.projet_prog_mobile.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.projet_prog_mobile.presentation.screens.home_screen.HomeScreen


@Composable
fun MainScreen(){
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        HomeScreen()
    }

}