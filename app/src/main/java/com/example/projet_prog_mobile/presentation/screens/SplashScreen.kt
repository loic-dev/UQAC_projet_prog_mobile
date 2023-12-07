package com.example.projet_prog_mobile.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.state.AuthState
import com.example.projet_prog_mobile.presentation.viewModel.AuthViewModel

@Composable
fun SplashScreen(
    modifier:Modifier,
    authViewModel: AuthViewModel = hiltViewModel() ,
    onLoginSuccessNavigation: () -> Unit,
    onLoginFailedNavigation: () -> Unit
) {

    val authState: AuthState by authViewModel.authState.observeAsState(AuthState())

    LaunchedEffect(true) {
        authViewModel.checkAuth()
    }

    LaunchedEffect(authState.loading) {
        if(!authState.loading){
            if (authState.auth) {
                onLoginSuccessNavigation()
            } else {
                onLoginFailedNavigation()
            }
        }
    }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_smartshop),
                contentDescription = "Logo smartshop"

            )
        }






}