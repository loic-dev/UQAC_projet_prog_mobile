package com.example.projet_prog_mobile.presentation.state

data class LoginState(
    val emailInput: String = "",
    val passwordInput:String = "",
    val isInputValid:Boolean = false,
    val isPasswordShown:Boolean = false,
    val errorMessageInput:String? = null,
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val errorMessageLogin:String? = null
)