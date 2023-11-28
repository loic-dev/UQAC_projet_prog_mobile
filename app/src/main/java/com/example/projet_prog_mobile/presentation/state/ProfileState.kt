package com.example.projet_prog_mobile.presentation.state

data class ProfileState(
    val errorMessageApi: String? = null,
    val isLoading: Boolean = false,
    val logoutSuccess: Boolean = false,
    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
)