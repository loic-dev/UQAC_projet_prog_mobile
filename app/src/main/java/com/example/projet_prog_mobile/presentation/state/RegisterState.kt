package com.example.projet_prog_mobile.presentation.state

data class RegisterState(
    val firstNameInput: String = "",
    val errorMessageFirstname:String? = null,
    val isInputFirstNameValid:Boolean = false,
    val lastNameInput:String = "",
    val errorMessageLastname:String? = null,
    val isInputLastNameValid:Boolean = false,
    val emailInput: String = "",
    val errorMessageEmail:String? = null,
    val isInputEmailValid:Boolean = false,
    val passwordInput:String = "",
    val errorMessagePassword:String? = null,
    val isInputPasswordValid:Boolean = false,
    val passwordConfirmInput:String = "",
    val errorMessagePasswordConfirm:String? = null,
    val isInputPasswordConfirmValid:Boolean = false,
    val errorMessageAPI:String? = null,
    val errorMessageInput:String? = null,
    val isLoading: Boolean = false,
    val isSuccessRegister: Boolean = false,
)

