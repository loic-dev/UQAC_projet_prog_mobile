package com.example.projet_prog_mobile.data.api.user

data class LoginRequest(
    val email:String,
    val password:String
)
data class RegisterRequest(
    val firstName:String,
    val lastName:String,
    val email:String,
    val password:String
)
