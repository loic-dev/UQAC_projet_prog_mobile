package com.example.projet_prog_mobile.domain.repository

interface UserRepository {
    suspend fun authUser(): Boolean
}