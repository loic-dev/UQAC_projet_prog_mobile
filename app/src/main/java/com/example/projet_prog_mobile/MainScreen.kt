package com.example.projet_prog_mobile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projet_prog_mobile.components.bottomBar.BottomBar
import com.example.projet_prog_mobile.components.bottomBar.BottomNavBarGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = {
            BottomBar(navController = navController)
        }
    ){
        BottomNavBarGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBar.Home,
        BottomBar.Search,
        BottomBar.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBar,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "Nav bar icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        }
    )
}