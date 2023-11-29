package com.example.projet_prog_mobile.presentation.screens.main_screen.profile_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projet_prog_mobile.presentation.components.NavDestinationHelper
import com.example.projet_prog_mobile.presentation.containers.ProfileContainer
import com.example.projet_prog_mobile.presentation.viewModel.ProfileViewModel
import com.example.projet_prog_mobile.util.ScreenRoutes

@Composable
fun ProfileScreen(modifier: Modifier,
                  navController: NavController,
                  profileViewModel: ProfileViewModel = hiltViewModel()) {
    NavDestinationHelper(
        shouldNavigate = {
            profileViewModel.profileState.logoutSuccess
        },
        destination = {
            navController.navigate(ScreenRoutes.LoginScreen.route)
        }
    )

    LaunchedEffect(true) {
        profileViewModel.onLoad()
    }

    ProfileContainer(isLoading = profileViewModel.profileState.isLoading,
        firstname = profileViewModel.profileState.firstname ,
        lastname = profileViewModel.profileState.lastname,
        email = profileViewModel.profileState.email,
        modifier = modifier,
        onLogoutButtonClick = profileViewModel::onDisconnect)
}