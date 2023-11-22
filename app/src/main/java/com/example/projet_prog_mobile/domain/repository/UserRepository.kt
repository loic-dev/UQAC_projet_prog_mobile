package com.example.projet_prog_mobile.domain.repository

interface UserRepository {
    suspend fun authUser(): Boolean
    suspend fun loginUser(email:String, password:String):Boolean
    suspend fun registerUser(firstName: String, lastName: String, email: String, password: String): Boolean
}