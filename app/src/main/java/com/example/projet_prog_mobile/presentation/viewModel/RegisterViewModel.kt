package com.example.projet_prog_mobile.presentation.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.projet_prog_mobile.presentation.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.projet_prog_mobile.data.api.ApiException
import com.example.projet_prog_mobile.domain.model.EmailInputValidationType
import com.example.projet_prog_mobile.domain.model.PasswordConfirmInputValidationType
import com.example.projet_prog_mobile.domain.model.PasswordInputValidationType
import com.example.projet_prog_mobile.domain.model.TextInputValidationType
import com.example.projet_prog_mobile.domain.repository.UserRepository
import com.example.projet_prog_mobile.domain.use_cases.ValidateAuthInputTextUseCase
import com.example.projet_prog_mobile.domain.use_cases.ValidateConfirmPasswordInputUseCase
import com.example.projet_prog_mobile.domain.use_cases.ValidateEmailInputUseCase
import com.example.projet_prog_mobile.domain.use_cases.ValidatePasswordInputUseCase
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateEmailInputUseCase: ValidateEmailInputUseCase,
    private val validatePasswordInputUseCase: ValidatePasswordInputUseCase,
    private val validateConfirmPasswordInputUseCase: ValidateConfirmPasswordInputUseCase,
    private val validateAuthInputTextUseCase: ValidateAuthInputTextUseCase,
    private val userRepository: UserRepository
): ViewModel() {
    var registerState by mutableStateOf(RegisterState())
        private set

    fun onFirstNameInputChange(newValue: String){
        registerState = registerState.copy(firstNameInput = newValue)
        checkInputFirstname()
    }
    fun onLastNameInputChange(newValue: String){
        registerState = registerState.copy(lastNameInput = newValue)
        checkInputLastname()
    }
    fun onEmailInputChange(newValue: String){
        registerState = registerState.copy(emailInput = newValue)
        checkEmail()
    }

    fun onPasswordInputChange(newValue: String){
        registerState = registerState.copy(passwordInput = newValue)
        checkPassword()
    }

    fun onPasswordConfirmInputChange(newValue: String){
        registerState = registerState.copy(passwordConfirmInput = newValue)
        checkConfirmPassword()
    }

    fun onRegisterClick(){
        registerState = registerState.copy(isLoading = true)
        viewModelScope.launch {
            try{
                val registerResult = userRepository.registerUser(registerState.firstNameInput, registerState.lastNameInput, registerState.emailInput, registerState.passwordInput)
                registerState = registerState.copy(isSuccessRegister = registerResult)
            }catch(e: ApiException){
                registerState = registerState.copy(isSuccessRegister = false, errorMessageAPI = e.message)
            }finally {
                registerState = registerState.copy(
                    isLoading = false,
                    emailInput = "",
                    firstNameInput = "",
                    lastNameInput = "",
                    passwordInput = "",
                    passwordConfirmInput = "")
            }
        }
    }



    private fun checkInputFirstname(){
        val validationResult = validateAuthInputTextUseCase(registerState.firstNameInput)
        registerState = when(validationResult){
            TextInputValidationType.EmptyField -> {
                registerState.copy(errorMessageFirstname = "Empty fields left", isInputFirstNameValid = false)
            }
            TextInputValidationType.Valid -> {
                registerState.copy(errorMessageFirstname = null, isInputFirstNameValid = true)
            }
        }
    }

    private fun checkInputLastname(){
        val validationResult = validateAuthInputTextUseCase(registerState.lastNameInput)
        registerState = when(validationResult){
            TextInputValidationType.EmptyField -> {
                registerState.copy(errorMessageLastname = "Empty fields left", isInputLastNameValid = false)
            }
            TextInputValidationType.Valid -> {
                registerState.copy(errorMessageLastname = null, isInputLastNameValid = true)
            }
        }
    }


    private fun checkEmail(){
        val validationResult = validateEmailInputUseCase(registerState.emailInput)
        registerState = when(validationResult){
            EmailInputValidationType.EmptyField -> {
                registerState.copy(errorMessageEmail = "Empty fields left", isInputEmailValid = false)
            }
            EmailInputValidationType.NoEmail -> {
                registerState.copy(errorMessageEmail = "No valid email", isInputEmailValid = false)
            }
            EmailInputValidationType.Valid -> {
                registerState.copy(errorMessageEmail = null, isInputEmailValid = true)
            }
        }

    }
    private fun checkPassword(){
        val validationResult = validatePasswordInputUseCase(registerState.passwordInput)
        registerState = when(validationResult){
            PasswordInputValidationType.EmptyField -> {
                registerState.copy(errorMessagePassword = "Empty fields left", isInputPasswordValid = false)
            }
            PasswordInputValidationType.NoPassword -> {
                registerState.copy(errorMessagePassword = "Invalid password", isInputPasswordValid = false)
            }
            PasswordInputValidationType.Valid -> {
                registerState.copy(errorMessagePassword = null, isInputPasswordValid = true)
            }
        }
    }

    private fun checkConfirmPassword(){
        val validationResult = validateConfirmPasswordInputUseCase(registerState.passwordInput,registerState.passwordConfirmInput)
        registerState = when(validationResult){
            PasswordConfirmInputValidationType.EmptyField -> {
                registerState.copy(errorMessagePasswordConfirm = "Empty fields left", isInputPasswordConfirmValid = false)
            }
            PasswordConfirmInputValidationType.WrongPassword -> {
                registerState.copy(errorMessagePasswordConfirm = "Not same password", isInputPasswordConfirmValid = false)
            }
            PasswordConfirmInputValidationType.Valid -> {
                registerState.copy(errorMessagePasswordConfirm = null, isInputPasswordConfirmValid = true)
            }
        }
    }




}