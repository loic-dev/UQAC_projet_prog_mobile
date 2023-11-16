package com.example.projet_prog_mobile.presentation.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.projet_prog_mobile.presentation.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.projet_prog_mobile.domain.model.RegisterInputValidationType
import com.example.projet_prog_mobile.domain.repository.UserRepository
import com.example.projet_prog_mobile.domain.use_cases.ValidateRegisterInputUseCase
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase,
    private val userRepository: UserRepository
): ViewModel() {
    var registerState by mutableStateOf(RegisterState())
        private set

    fun onFirstNameInputChange(newValue: String){
        registerState = registerState.copy(firstNameInput = newValue)
        checkInputValidation()
    }
    fun onLastNameInputChange(newValue: String){
        registerState = registerState.copy(lastNameInput = newValue)
        checkInputValidation()
    }
    fun onEmailInputChange(newValue: String){
        registerState = registerState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String){
        registerState = registerState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onPasswordConfirmInputChange(newValue: String){
        registerState = registerState.copy(passwordConfirmInput = newValue)
        checkInputValidation()
    }

    fun onRegisterClick(){
        registerState = registerState.copy(isLoading = true)
        viewModelScope.launch {
            registerState = try{

                val registerResult = userRepository.registerUser(registerState.firstNameInput, registerState.lastNameInput, registerState.emailInput, registerState.passwordInput)
                registerState.copy(isSuccessRegister = registerResult)
            }catch(e: Exception){
                registerState.copy(errorMessageRegister = "Could not register")
            }finally {
                registerState = registerState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation(){
        val validationResult = validateRegisterInputUseCase(
            registerState.firstNameInput,
            registerState.lastNameInput,
            registerState.emailInput,
            registerState.passwordInput,
            registerState.passwordConfirmInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: RegisterInputValidationType){
        registerState = when(type){
            RegisterInputValidationType.EmptyField -> {
                registerState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }
            RegisterInputValidationType.NoEmail -> {
                registerState.copy(errorMessageInput = "No valid email", isInputValid = false)
            }
            RegisterInputValidationType.WrongPassword -> {
                registerState.copy(errorMessageInput = "The two password are not the same", isInputValid = false)
            }
            RegisterInputValidationType.Valid -> {
                registerState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }


}