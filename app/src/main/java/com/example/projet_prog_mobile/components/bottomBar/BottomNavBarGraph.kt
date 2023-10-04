package com.example.projet_prog_mobile.components.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projet_prog_mobile.screens.HomeScreen
import com.example.projet_prog_mobile.screens.ProfileScreen
import com.example.projet_prog_mobile.screens.SearchScreen

@Composable
fun BottomNavBarGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBar.Home.route)
    {
        composable(route = BottomBar.Home.route){
            HomeScreen()
        }
        composable(route = BottomBar.Search.route){
            SearchScreen()
        }
        composable(route = BottomBar.Profile.route){
            ProfileScreen()
        }
    }
}