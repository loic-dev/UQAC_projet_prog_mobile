package com.example.projet_prog_mobile.presentation.containers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.components.AuthInputButton
import com.example.projet_prog_mobile.presentation.components.AuthInputText
import com.example.projet_prog_mobile.presentation.ui.theme.Projet_prog_mobileTheme
import com.example.projet_prog_mobile.util.ScreenRoutes
import com.example.projet_prog_mobile.util.WindowInfo
import com.example.projet_prog_mobile.util.rememberWindowInfo

@Composable
fun RegisterContainer(
    modifier: Modifier,
    firstNameValue:() -> String,
    onFirstNameValueChanged:(String) -> Unit,
    errorFirstname:()->String?,
    lastNameValue:() -> String,
    onLastNameValueChanged:(String) -> Unit,
    errorLastname:()->String?,
    emailValue:() -> String,
    onEmailChanged:(String) -> Unit,
    errorEmail:()->String?,
    passwordValue:()-> String,
    onPasswordChanged:(String) -> Unit,
    errorPassword:()->String?,
    passwordConfirmValue:()-> String,
    onPasswordConfirmChanged:(String) -> Unit,
    errorConfirmPassword:()->String?,
    errorAPI:()->String?,
    buttonEnabled:() -> Boolean,
    onRegisterButtonClick:()->Unit,
    isLoading:()->Boolean,
    navController: NavController,
) {

    Projet_prog_mobileTheme {
        val windowInfo = rememberWindowInfo()
        var passwordVisible by remember { mutableStateOf(false) }
        var confirmPasswordVisible by remember { mutableStateOf(false) }


        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            Column(modifier=modifier,verticalArrangement = Arrangement.spacedBy(10.dp)){
                if(errorAPI() != null){
                    Text(
                        text = errorAPI() ?: "",
                        color= MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                    AuthInputText(
                        modifier= Modifier.weight(1f),
                        value=firstNameValue,
                        onValueChanged = onFirstNameValueChanged,
                        placeholder = stringResource(R.string.firstname_input_placeholder),
                        keyboardType = KeyboardType.Ascii,
                        error = errorFirstname)
                    AuthInputText(
                        modifier= Modifier.weight(1f),
                        value=lastNameValue,
                        onValueChanged = onLastNameValueChanged,
                        placeholder = stringResource(R.string.lastname_input_placeholder),
                        keyboardType = KeyboardType.Ascii,
                        error = errorLastname)
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
                AuthInputText(
                    modifier= Modifier.fillMaxWidth(),
                    value=passwordConfirmValue,
                    onValueChanged = onPasswordConfirmChanged,
                    placeholder = stringResource(R.string.confirm_password_input_placeholder),
                    keyboardType = KeyboardType.Password,
                    error = errorConfirmPassword,
                    visualTransformation = if(confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = { confirmPasswordVisible = !confirmPasswordVisible }
                        ) {
                            Icon(
                                imageVector = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = if (confirmPasswordVisible) stringResource(R.string.blind_text_hide_password) else stringResource(
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
                    onButtonClick=onRegisterButtonClick,
                    textButton=stringResource(R.string.register_page_button_signup)
                )
                val annotatedString = buildAnnotatedString {
                    append(stringResource(R.string.register_page_link_to_login_question))
                    withStyle(style = SpanStyle(color = Color(0xFFDE3163))) {
                        append(stringResource(R.string.register_page_link_to_login_text))
                    }
                }
                ClickableText(
                    modifier = Modifier.fillMaxWidth(),
                    text = annotatedString,
                    style = TextStyle(
                        textAlign = TextAlign.Center),
                    onClick = {
                        navController.navigate(ScreenRoutes.LoginScreen.route)

                    }
                )
            }
        }
        else{
            Text(text = "Reaction to screen change")
        }
    }
}