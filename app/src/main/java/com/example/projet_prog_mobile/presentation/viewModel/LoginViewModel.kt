package com.example.projet_prog_mobile.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.projet_prog_mobile.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.projet_prog_mobile.domain.model.EmailInputValidationType
import com.example.projet_prog_mobile.domain.model.PasswordInputValidationType
import com.example.projet_prog_mobile.domain.repository.UserRepository
import com.example.projet_prog_mobile.domain.use_cases.ValidateEmailInputUseCase
import com.example.projet_prog_mobile.domain.use_cases.ValidatePasswordInputUseCase
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailInputUseCase: ValidateEmailInputUseCase,
    private val validatePasswordInputUseCase: ValidatePasswordInputUseCase,
    private val userRepository: UserRepository
): ViewModel() {
    var loginState by mutableStateOf(LoginState())
        private set


    fun onEmailInputChange(newValue: String){
        loginState = loginState.copy(emailInput = newValue)
        checkEmail()
    }

    fun onPasswordInputChange(newValue: String){
        loginState = loginState.copy(passwordInput = newValue)
        checkPassword()
    }
    private fun checkEmail(){
        val validationResult = validateEmailInputUseCase(loginState.emailInput)
        loginState = when(validationResult){
            EmailInputValidationType.EmptyField -> {
                loginState.copy(errorMessageEmail = "Empty fields left", isInputEmailValid = false)
            }
            EmailInputValidationType.NoEmail -> {
                loginState.copy(errorMessageEmail = "No valid email", isInputEmailValid = false)
            }
            EmailInputValidationType.Valid -> {
                loginState.copy(errorMessageEmail = null, isInputEmailValid = true)
            }
        }

    }
    private fun checkPassword(){
        val validationResult = validatePasswordInputUseCase(loginState.emailInput)
        loginState = when(validationResult){
            PasswordInputValidationType.EmptyField -> {
                loginState.copy(errorMessagePassword = "Empty fields left", isInputPasswordValid = false)
            }
            PasswordInputValidationType.NoPassword -> {
                loginState.copy(errorMessagePassword = "Invalid password", isInputPasswordValid = false)
            }
            PasswordInputValidationType.Valid -> {
                loginState.copy(errorMessageEmail = null, isInputPasswordValid = true)
            }
        }
    }

    fun onLoginClick(){
        loginState = loginState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val loginResult = userRepository.loginUser(loginState.emailInput, loginState.passwordInput)
                loginState = loginState.copy(isSuccessLogin = loginResult)
            } finally {
                loginState = loginState.copy(isLoading = false, emailInput = "", passwordInput = "")
            }
        }
    }


}