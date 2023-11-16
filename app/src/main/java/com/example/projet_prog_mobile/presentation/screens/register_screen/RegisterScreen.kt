package com.example.projet_prog_mobile.presentation.screens.register_screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projet_prog_mobile.presentation.components.NavDestinationHelper
import com.example.projet_prog_mobile.presentation.components.RegisterButton
import com.example.projet_prog_mobile.presentation.components.TextEntryModule
import com.example.projet_prog_mobile.presentation.ui.theme.Projet_prog_mobileTheme
import com.example.projet_prog_mobile.presentation.viewModel.RegisterViewModel
import com.example.projet_prog_mobile.util.ScreenRoutes
import com.example.projet_prog_mobile.util.WindowInfo
import com.example.projet_prog_mobile.util.rememberWindowInfo

@Composable
fun RegisterScreen(
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

        RegisterContainer(
            firstNameValue = { registerViewModel.registerState.firstNameInput },
            lastNameValue = { registerViewModel.registerState.lastNameInput },
            emailValue = { registerViewModel.registerState.emailInput },
            passwordValue = { registerViewModel.registerState.passwordInput },
            passwordConfirmValue = { registerViewModel.registerState.passwordConfirmInput },
            buttonEnabled = { registerViewModel.registerState.isInputValid },
            onFirstNameValueChanged = registerViewModel::onFirstNameInputChange,
            onLastNameValueChanged = registerViewModel::onLastNameInputChange,
            onEmailChanged = registerViewModel::onEmailInputChange,
            onPasswordChanged = registerViewModel::onPasswordInputChange,
            onPasswordConfirmChanged = registerViewModel::onPasswordConfirmInputChange,
            onRegisterButtonClick = registerViewModel::onRegisterClick,
            errorHint = { registerViewModel.registerState.errorMessageInput },
            isLoading = { registerViewModel.registerState.isLoading },
            navController = navController)
    }


@Composable
fun RegisterContainer(
    firstNameValue:() -> String,
    lastNameValue:() -> String,
    emailValue:() -> String,
    passwordValue:()-> String,
    passwordConfirmValue:()-> String,
    buttonEnabled:() -> Boolean,
    onFirstNameValueChanged:(String) -> Unit,
    onLastNameValueChanged:(String) -> Unit,
    onEmailChanged:(String) -> Unit,
    onPasswordChanged:(String) -> Unit,
    onPasswordConfirmChanged:(String) -> Unit,
    onRegisterButtonClick:()->Unit,
    errorHint:()->String?,
    isLoading:()->Boolean,
    navController: NavController,
) {

    Projet_prog_mobileTheme {
        val windowInfo = rememberWindowInfo()
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(0.dp)),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Let's Register Account",
                        fontSize = 30.sp,  // Taille plus grande pour le premier texte
                        modifier = Modifier.padding(bottom = 8.dp)  // Espacement vers le bas
                    )
                    Text(
                        text = "Hello, you have a greatful journey",
                        fontSize = 16.sp  // Taille normale pour le deuxième texte
                    )
                    Row(

                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TextEntryModule(
                            modifier = Modifier.weight(1f),
                            description = "",
                            hint = "FirstName",
                            textValue = firstNameValue(),
                            onValueChanged = onFirstNameValueChanged,
                        )

                        // Deuxième entrée de texte
                        TextEntryModule(
                            modifier = Modifier.weight(1f),
                            description = "",
                            hint = "Lastname",
                            textValue = lastNameValue(),
                            onValueChanged = onLastNameValueChanged,
                        )
                    }
                    TextEntryModule(
                        modifier = Modifier
                            .fillMaxWidth(),
                        description = "",
                        hint = "Email address",
                        textValue = emailValue(),
                        onValueChanged = onEmailChanged,
                    )
                    TextEntryModule(
                        description = "",
                        hint = "Enter password",
                        textValue = passwordValue(),
                        onValueChanged = onPasswordChanged,
                        keyboardType = KeyboardType.Password
                    )
                    TextEntryModule(
                        description = "",
                        hint = "Confirm password",
                        textValue = passwordConfirmValue(),
                        onValueChanged = onPasswordConfirmChanged,
                        keyboardType = KeyboardType.Password
                    )

                    RegisterButton(
                        text = "Register",
                        enabled = buttonEnabled(),
                        isLoading = isLoading(),
                        onButtonClick = onRegisterButtonClick
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    val annotatedString = buildAnnotatedString {
                        append("Already have an account ? ")
                        withStyle(style = SpanStyle(color = Color(0xFFDE3163))) {
                            append("Sign in")
                        }

                    }
                    ClickableText(
                        text = annotatedString,
                        modifier = Modifier.padding(start = 8.dp),
                        onClick = {
                            navController.navigate(ScreenRoutes.LoginScreen.route)

                        }
                    )
                }
                Text(errorHint() ?: "", modifier = Modifier.padding(top = 8.dp))
            }
        }
        else{

        }
    }
}