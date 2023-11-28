package com.example.projet_prog_mobile.presentation.screens.main_screen.profile_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.components.NavDestinationHelper
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

    Column(modifier=modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.profile_title)
                )
                Text(
                    fontSize = 15.sp,
                    text = stringResource(R.string.profile_firstname)
                )
                TODO()
            }

        }
    }
}