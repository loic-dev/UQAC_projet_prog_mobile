package com.example.projet_prog_mobile.domain.repository

import com.example.projet_prog_mobile.data.local.user.User

interface UserRepository {
    suspend fun authUser(): Boolean
    suspend fun loginUser(email:String, password:String):Boolean
    suspend fun registerUser(firstName: String, lastName: String, email: String, password: String): Boolean
    suspend fun getUser(): User
    suspend fun logout()
}