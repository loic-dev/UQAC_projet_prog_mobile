package com.example.projet_prog_mobile.presentation.state

data class RegisterState(
    val firstNameInput: String = "",
    val lastNameInput:String = "",
    val emailInput: String = "",
    val passwordInput:String = "",
    val passwordConfirmInput:String = "",
    val isInputValid:Boolean = false,
    val isPasswordShown:Boolean = false,
    val errorMessageInput:String? = null,
    val isLoading: Boolean = false,
    val isSuccessRegister: Boolean = false,
    val errorMessageRegister:String? = null
)