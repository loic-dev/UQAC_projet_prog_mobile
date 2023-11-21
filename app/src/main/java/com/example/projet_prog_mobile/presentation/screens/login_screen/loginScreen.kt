package com.example.projet_prog_mobile.presentation.screens.login_screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.components.NavDestinationHelper
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContainer(
    emailValue:() -> String,
    passwordValue:()-> String,
    buttonEnabled:() -> Boolean,
    onEmailChanged:(String) -> Unit,
    onPasswordChanged:(String) -> Unit,
    onLoginButtonClick:()->Unit,
    onErrorAPI:()->String?,
    errorEmail:()->String?,
    errorPassword:()->String?,
    isLoading:()->Boolean,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        if(onErrorAPI() != null){
            Text(
                text = onErrorAPI() ?: "",
                color= MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailValue(),
            onValueChange = onEmailChanged,
            singleLine = true,
            placeholder = { Text(text = stringResource(R.string.email_input_placeholder),color = colorResource(id = R.color.dark_grey))},
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = colorResource(id = R.color.main_pink),
                focusedBorderColor = colorResource(id = R.color.main_pink),
                unfocusedBorderColor =  colorResource(id = R.color.dark_grey),
                textColor = colorResource(id = R.color.main_pink)
            ),
        )
        if(errorEmail() != null) Text(text = errorEmail() ?: "", color=MaterialTheme.colors.error)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordValue(),
            visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = onPasswordChanged,
            singleLine = true,
            placeholder = { Text(text = stringResource(R.string.password_input_placeholder),color = colorResource(id = R.color.dark_grey))},
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = colorResource(id = R.color.main_pink),
                focusedBorderColor = colorResource(id = R.color.main_pink),
                unfocusedBorderColor =  colorResource(id = R.color.dark_grey),
                textColor = colorResource(id = R.color.main_pink)
            ),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (passwordVisible) stringResource(R.string.blind_text_hide_password) else stringResource(
                            R.string.blind_text_show_password
                        ),
                        tint = colorResource(id = R.color.dark_grey)
                    )
                }
            }

        )
        if(errorPassword() != null) Text(text = errorPassword() ?: "", color=MaterialTheme.colors.error)
        Button(
            modifier= Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = { onLoginButtonClick() },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.main_pink),
                contentColor = colorResource(id = R.color.white)),
            enabled = buttonEnabled()
        ){
            if(isLoading()){
                CircularProgressIndicator(
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.size(20.dp)
                )
            }else{
                Text(text = stringResource(R.string.login_page_button_text), color=colorResource(id = R.color.white))
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            Text(
                text = stringResource(R.string.login_page_link_to_signup_text_question),
            )
            Text(
                text = stringResource(R.string.login_page_link_to_signup_text),
                modifier = Modifier.clickable {
                    navController.navigate("register_screen")
                },
                color = colorResource(id = R.color.main_pink)
            )
        }
    }
}

