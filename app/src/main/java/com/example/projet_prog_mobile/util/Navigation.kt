package com.example.projet_prog_mobile.util


import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projet_prog_mobile.presentation.screens.MainScreen
import com.example.projet_prog_mobile.presentation.screens.SplashScreen
import com.example.projet_prog_mobile.presentation.screens.login_screen.LoginScreen
import com.example.projet_prog_mobile.presentation.screens.main_screen.scan_screen.ScanScreen
import com.example.projet_prog_mobile.presentation.screens.register_screen.RegisterScreen

@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.SplashScreen.route){
        composable(ScreenRoutes.SplashScreen.route){
            SplashScreen(
                modifier = modifier,
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
                modifier = modifier,
                onLoginSuccessNavigation = {
                    navController.navigate(ScreenRoutes.HomeScreen.route){
                        popUpTo(0)
                    }
                },
                navController = navController
            )
        }

        composable(ScreenRoutes.RegisterScreen.route){
            RegisterScreen(
                modifier = modifier,
                onRegisterSuccessNavigation = {
                    navController.navigate(ScreenRoutes.LoginScreen.route){
                        popUpTo(0)
                    }
                },
                navController = navController
            )
        }

        composable(ScreenRoutes.HomeScreen.route){
            Surface {
                MainScreen(modifier, navController)
            }
        }

        composable(ScreenRoutes.ScanScreen.route){
            Surface {
                ScanScreen(navController = navController)
            }
        }
    }
}

sealed class ScreenRoutes(val route:String){
    data object SplashScreen:ScreenRoutes("splash_screen")
    data object LoginScreen:ScreenRoutes("login_screen")
    data object RegisterScreen:ScreenRoutes("register_screen")
    data object HomeScreen:ScreenRoutes("home_screen")
    data object ScanScreen:ScreenRoutes("scan_screen")
}