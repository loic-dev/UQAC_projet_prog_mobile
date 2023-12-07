package com.example.projet_prog_mobile.presentation.screens.login_screen



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.components.NavDestinationHelper
import com.example.projet_prog_mobile.presentation.containers.LoginContainer
import com.example.projet_prog_mobile.presentation.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier,
    onLoginSuccessNavigation: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavController

) {
    NavDestinationHelper(
        shouldNavigate = {
            loginViewModel.loginState.isSuccessLogin
        },
        destination = {
            onLoginSuccessNavigation()
        }
    )

    Column(modifier=modifier) {
        Box(modifier= Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp)){
            Column(
                modifier=Modifier
                        .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                    Text(modifier= Modifier.padding(bottom = 5.dp),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        text = stringResource(R.string.login_page_title)
                    )
                    Text(fontSize = 15.sp,
                        text = stringResource(R.string.login_page_subtitle)
                    )
            }

        }
        LoginContainer(
            modifier=Modifier,
            emailValue = { loginViewModel.loginState.emailInput },
            passwordValue = { loginViewModel.loginState.passwordInput },
            buttonEnabled = { loginViewModel.loginState.isInputEmailValid && loginViewModel.loginState.isInputPasswordValid },
            onEmailChanged = loginViewModel::onEmailInputChange,
            onErrorAPI= {loginViewModel.loginState.errorMessageAPI},
            onPasswordChanged = loginViewModel::onPasswordInputChange,
            onLoginButtonClick = loginViewModel::onLoginClick,
            errorEmail = {loginViewModel.loginState.errorMessageEmail},
            errorPassword = {loginViewModel.loginState.errorMessagePassword},
            isLoading = { loginViewModel.loginState.isLoading },
            navController = navController)
    }

}



