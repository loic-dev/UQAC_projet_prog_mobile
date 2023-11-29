package com.example.projet_prog_mobile.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.projet_prog_mobile.util.BottomNavBarGraph
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet_prog_mobile.presentation.components.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier:Modifier, navController:NavHostController){
    val navBottomController = rememberNavController()
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navBottomController = navBottomController) }
    ){
        BottomNavBarGraph(modifier=modifier,navBottomController = navBottomController,navController=navController )
    }
}

