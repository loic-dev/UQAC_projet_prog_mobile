package com.example.projet_prog_mobile.presentation.screens.main_screen.profile_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.components.AuthInputButton
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

    Column(modifier=modifier) {
        Text(
            text = "PROFILE",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.light_black)
            )
        )
        ProfileContainer(
            firstname = profileViewModel.profileState.firstname ,
            lastname = profileViewModel.profileState.lastname,
            email = profileViewModel.profileState.email,
            modifier = Modifier)
        AuthInputButton(
            modifier=Modifier,
            buttonEnabled= { true },
            isLoading= { profileViewModel.profileState.isLoading },
            onButtonClick= {profileViewModel.onDisconnect()},
            textButton= stringResource(R.string.profile_logout)
        )
    }
}