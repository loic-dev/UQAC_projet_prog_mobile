package com.example.projet_prog_mobile.components.bottomBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projet_prog_mobile.screens.HomeScreen
import com.example.projet_prog_mobile.screens.ProfileScreen
import com.example.projet_prog_mobile.screens.SearchScreen

@Composable
fun BottomNavBarGraph(navController: NavHostController, modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = BottomBar.Home.route,
        modifier = modifier
    )
    {
        composable(route = BottomBar.Home.route){
            HomeScreen(modifier)
        }
        composable(route = BottomBar.Search.route){
            SearchScreen(modifier)
        }
        composable(route = BottomBar.Profile.route){
            ProfileScreen(modifier)
        }
    }
}