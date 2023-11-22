package com.example.projet_prog_mobile.presentation.containers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.components.AuthInputButton
import com.example.projet_prog_mobile.presentation.components.AuthInputText


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
        AuthInputText(
            modifier= Modifier.fillMaxWidth(),
            value=emailValue,
            onValueChanged = onEmailChanged,
            placeholder = stringResource(id = R.string.email_input_placeholder),
            keyboardType = KeyboardType.Ascii,
            error = errorEmail)
        AuthInputText(
            modifier= Modifier.fillMaxWidth(),
            value=passwordValue,
            onValueChanged = onPasswordChanged,
            placeholder = stringResource(id = R.string.password_input_placeholder),
            keyboardType = KeyboardType.Password,
            error = errorPassword,
            visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
            })
        AuthInputButton(
            modifier=Modifier,
            buttonEnabled=buttonEnabled,
            isLoading=isLoading,
            onButtonClick=onLoginButtonClick,
            textButton=stringResource(R.string.login_page_button_text)
        )
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