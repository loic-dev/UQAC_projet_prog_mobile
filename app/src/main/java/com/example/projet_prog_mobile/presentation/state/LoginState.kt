package com.example.projet_prog_mobile.presentation.state

data class LoginState(
    val emailInput: String = "",
    val errorMessageEmail:String? = null,
    val isInputEmailValid:Boolean = false,
    val passwordInput:String = "",
    val errorMessagePassword:String? = null,
    val isInputPasswordValid:Boolean = false,
    val isPasswordShown:Boolean = false,
    val errorMessageAPI:String? = null,
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val errorMessageLogin:String? = null
)