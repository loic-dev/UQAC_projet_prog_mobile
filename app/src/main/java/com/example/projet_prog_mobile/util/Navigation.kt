package com.example.projet_prog_mobile.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projet_prog_mobile.presentation.screens.MainScreen
import com.example.projet_prog_mobile.presentation.screens.SplashScreen
import com.example.projet_prog_mobile.presentation.screens.login_screen.LoginScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.SplashScreen.route){
        composable(ScreenRoutes.SplashScreen.route){
            SplashScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(ScreenRoutes.HomeScreen.route){
                        popUpTo(0)
                    }
                },
                onLoginFailedNavigation = {
                    navController.navigate(ScreenRoutes.LoginScreen.route){
                        popUpTo(0)
                    }
                }

            )
        }
        composable(ScreenRoutes.LoginScreen.route){
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(ScreenRoutes.HomeScreen.route){
                        popUpTo(0)
                    }
                }
            )
        }
        composable(ScreenRoutes.HomeScreen.route){
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainScreen()
            }
        }
    }
}

sealed class ScreenRoutes(val route:String){
    object SplashScreen:ScreenRoutes("splash_screen")
    object LoginScreen:ScreenRoutes("login_screen")
    object HomeScreen:ScreenRoutes("home_screen")
}