package com.example.projet_prog_mobile.data.repository

import com.example.projet_prog_mobile.data.api.ApiResponse
import com.example.projet_prog_mobile.data.api.user.UserRemoteDataSource
import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import com.example.projet_prog_mobile.domain.repository.UserRepository
import kotlinx.coroutines.delay
import retrofit2.Response

class UserRepositoryImpl(
        private val userLocalDataSource: UserLocalDataSource,
        private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun authUser(): Boolean {
        val localToken = "deuhduehdhue"
        val result = userRemoteDataSource.auth(localToken)
        return result.auth;
    }
}
