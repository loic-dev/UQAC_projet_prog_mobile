package com.example.projet_prog_mobile.presentation.screens.login_screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.projet_prog_mobile.presentation.components.AuthButton
import com.example.projet_prog_mobile.presentation.components.NavDestinationHelper
import com.example.projet_prog_mobile.presentation.components.TextEntryModule
import com.example.projet_prog_mobile.presentation.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccessNavigation: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    NavDestinationHelper(
        shouldNavigate = {
            loginViewModel.loginState.isSuccessLogin
        },
        destination = {
            onLoginSuccessNavigation()
        }
    )

    Box {
        Box(contentAlignment = Alignment.Center){
            Text(text = "Login")
        }
        LoginContainer(
            emailValue = { loginViewModel.loginState.emailInput },
            passwordValue = { loginViewModel.loginState.passwordInput },
            buttonEnabled = { loginViewModel.loginState.isInputValid },
            onEmailChanged = loginViewModel::onEmailInputChange,
            onPasswordChanged = loginViewModel::onPasswordInputChange,
            onLoginButtonClick = loginViewModel::onLoginClick,
            errorHint = { loginViewModel.loginState.errorMessageInput },
            isLoading = { loginViewModel.loginState.isLoading })
    }

}

@Composable
fun LoginContainer(
    emailValue:() -> String,
    passwordValue:()-> String,
    buttonEnabled:() -> Boolean,
    onEmailChanged:(String) -> Unit,
    onPasswordChanged:(String) -> Unit,
    onLoginButtonClick:()->Unit,
    errorHint:()->String?,
    isLoading:()->Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Email address",
            hint = "KApps@gmail.com",
            textValue = emailValue(),
            onValueChanged = onEmailChanged,
        )
        TextEntryModule(
            description = "Password",
            hint = "Enter password",
            textValue = passwordValue(),
            onValueChanged = onPasswordChanged,
            keyboardType = KeyboardType.Password
        )
        Column{
            AuthButton(
                text = "Login",
                enabled = buttonEnabled(),
                isLoading = isLoading(),
                onButtonClick = onLoginButtonClick
            )
            Text(errorHint() ?: "")
        }
    }
}