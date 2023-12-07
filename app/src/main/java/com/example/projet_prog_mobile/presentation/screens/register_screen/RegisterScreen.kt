package com.example.projet_prog_mobile.presentation.screens.register_screen


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
import com.example.projet_prog_mobile.presentation.containers.RegisterContainer
import com.example.projet_prog_mobile.presentation.viewModel.RegisterViewModel

@Composable
fun RegisterScreen(
    modifier:Modifier,
    onRegisterSuccessNavigation: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navController: NavController
) {

    NavDestinationHelper(
        shouldNavigate = {
            registerViewModel.registerState.isSuccessRegister
        },
        destination = {
            onRegisterSuccessNavigation()
        }
    )

    Column(modifier=modifier) {
        Box(modifier= Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp)){
            Column(
                modifier=Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(modifier= Modifier.padding(bottom = 5.dp),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.register_page_title)
                )
                Text(fontSize = 15.sp,
                    text = stringResource(R.string.register_page_subtitle)
                )
            }

        }
        RegisterContainer(
            modifier=Modifier,
            firstNameValue = { registerViewModel.registerState.firstNameInput },
            onFirstNameValueChanged = registerViewModel::onFirstNameInputChange,
            errorFirstname = { registerViewModel.registerState.errorMessageFirstname },
            lastNameValue = { registerViewModel.registerState.lastNameInput },
            onLastNameValueChanged = registerViewModel::onLastNameInputChange,
            errorLastname = { registerViewModel.registerState.errorMessageLastname },
            emailValue = { registerViewModel.registerState.emailInput },
            onEmailChanged = registerViewModel::onEmailInputChange,
            errorEmail = { registerViewModel.registerState.errorMessageEmail },
            passwordValue = { registerViewModel.registerState.passwordInput },
            onPasswordChanged = registerViewModel::onPasswordInputChange,
            errorPassword = { registerViewModel.registerState.errorMessagePassword },
            passwordConfirmValue = { registerViewModel.registerState.passwordConfirmInput },
            onPasswordConfirmChanged = registerViewModel::onPasswordConfirmInputChange,
            errorConfirmPassword = { registerViewModel.registerState.errorMessagePasswordConfirm },
            buttonEnabled = { registerViewModel.registerState.isInputFirstNameValid &&
                        registerViewModel.registerState.isInputLastNameValid &&
                        registerViewModel.registerState.isInputEmailValid &&
                        registerViewModel.registerState.isInputPasswordValid &&
                        registerViewModel.registerState.isInputPasswordConfirmValid },
            errorAPI = {registerViewModel.registerState.errorMessageAPI},
            onRegisterButtonClick = registerViewModel::onRegisterClick,
            isLoading = { registerViewModel.registerState.isLoading },
            navController = navController)
    }
}


