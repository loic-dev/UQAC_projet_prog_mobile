package com.example.projet_prog_mobile.data.repository

import com.example.projet_prog_mobile.data.api.ApiResponse
import com.example.projet_prog_mobile.data.api.user.UserRemoteDataSource
import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import com.example.projet_prog_mobile.domain.repository.UserRepository

class UserRepositoryImpl(
        private val userLocalDataSource: UserLocalDataSource,
        private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun authUser(): Boolean {
        val localToken = userLocalDataSource.getUserEntity().token
        var auth = false;
        if (localToken != null) {
            auth = try {
                val response = userRemoteDataSource.auth(localToken)
                when (response) {
                    is ApiResponse.Success -> {
                        response.data.auth
                    }

                    is ApiResponse.Error -> {
                        false
                    }
                }
            } catch (e: Exception) {
                false
            }
        }
        return auth;
    }
}
