package com.example.projet_prog_mobile.data.api.user

data class UserLoginResponse(
    val token:String,
    val user:UserDetail
)

data class UserDetail(
    val firstname:String,
    val lastname:String,
    val email:String
)