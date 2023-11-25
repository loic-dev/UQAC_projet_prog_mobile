package com.example.projet_prog_mobile.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projet_prog_mobile.presentation.screens.main_screen.home_screen.HomeScreen
import com.example.projet_prog_mobile.presentation.screens.main_screen.profile_screen.ProfileScreen
import com.example.projet_prog_mobile.presentation.screens.main_screen.shop_screen.ShopScreen

@Composable
fun BottomNavBarGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBar.Home.route)
    {
        composable(route = BottomBar.Home.route){
            HomeScreen()
        }
        composable(route = BottomBar.Shop.route){
            ShopScreen()
        }
        composable(route = BottomBar.Profile.route){
            ProfileScreen()
        }
    }
}

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home: BottomBar(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    data object Shop: BottomBar(
        route = "shop",
        title = "Shopping",
        icon = Icons.Default.ShoppingCart
    )
    data object Profile: BottomBar(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.AccountCircle
    )
}