package com.example.projet_prog_mobile.components.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBar(
      route = "home",
      title = "Home",
      icon = Icons.Default.Home
    )
    object Search: BottomBar(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search
    )
    object Profile: BottomBar(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.AccountCircle
    )
}