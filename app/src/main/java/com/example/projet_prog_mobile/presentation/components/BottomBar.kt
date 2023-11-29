package com.example.projet_prog_mobile.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.projet_prog_mobile.util.BottomBar
import com.example.projet_prog_mobile.R

@Composable
fun BottomBar(navBottomController: NavHostController){
    val screens = listOf(
        BottomBar.Home,
        BottomBar.Shop,
        BottomBar.Profile
    )

    val navBackStackEntry by navBottomController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor= colorResource(id = R.color.light_grey)
    ){
        screens.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navBottomController)
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
        selectedContentColor = colorResource(id = R.color.main_pink),
        unselectedContentColor = colorResource(id = R.color.light_black),
        label = {
            Text(text = screen.title)
        },
        icon = {
            val iconColor = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                R.color.main_pink
            } else {
                R.color.light_black
            }
            Icon(imageVector = screen.icon, contentDescription = "Nav bar icon", tint = colorResource(iconColor))
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        }
    )
}
