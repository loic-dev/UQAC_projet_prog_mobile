package com.example.projet_prog_mobile.presentation.screens.register_screen


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
import com.example.projet_prog_mobile.presentation.components.NavDestinationHelper
import com.example.projet_prog_mobile.presentation.components.RegisterButton
import com.example.projet_prog_mobile.presentation.components.TextEntryModule
import com.example.projet_prog_mobile.presentation.viewModel.RegisterViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccessNavigation: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {

    NavDestinationHelper(
        shouldNavigate = {
            registerViewModel.registerState.isSuccessRegister
        },
        destination = {
            onRegisterSuccessNavigation()
        }
    )

    Box {
        Box(contentAlignment = Alignment.Center){
            Text(text = "Register")
        }
        RegisterContainer(
            emailValue = { registerViewModel.registerState.emailInput },
            passwordValue = { registerViewModel.registerState.passwordInput },
            passwordValue2 = { registerViewModel.registerState.passwordInput },
            buttonEnabled = { registerViewModel.registerState.isInputValid },
            onEmailChanged = registerViewModel::onEmailInputChange,
            onPasswordChanged = registerViewModel::onPasswordInputChange,
            onRegisterButtonClick = registerViewModel::onRegisterClick,
            errorHint = { registerViewModel.registerState.errorMessageInput },
            isLoading = { registerViewModel.registerState.isLoading })
    }

}

@Composable
fun RegisterContainer(
    emailValue:() -> String,
    passwordValue:()-> String,
    passwordValue2:()-> String,
    buttonEnabled:() -> Boolean,
    onEmailChanged:(String) -> Unit,
    onPasswordChanged:(String) -> Unit,
    onRegisterButtonClick:()->Unit,
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
        TextEntryModule(
            description = "Password",
            hint = "Enter the same password",
            textValue = passwordValue2(),
            onValueChanged = onPasswordChanged,
            keyboardType = KeyboardType.Password
        )
        Column{
            RegisterButton(
                text = "Register",
                enabled = buttonEnabled(),
                isLoading = isLoading(),
                onButtonClick = onRegisterButtonClick
            )
            Text(errorHint() ?: "")
        }
    }
}