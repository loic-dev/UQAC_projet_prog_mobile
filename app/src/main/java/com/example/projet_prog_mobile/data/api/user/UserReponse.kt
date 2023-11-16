package com.example.projet_prog_mobile.data.api.user

data class UserAuthResponse(
    val auth: Boolean,
)
data class UserLoginResponse(
    val token:String,
    val firstname:String,
    val lastname:String,
    val email:String
)