package com.example.projet_prog_mobile.data.repository


import com.example.projet_prog_mobile.data.api.user.UserRemoteDataSource
import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import com.example.projet_prog_mobile.domain.repository.UserRepository


class UserRepositoryImpl(
        private val userLocalDataSource: UserLocalDataSource,
        private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun authUser(): Boolean {
        val localToken = "deuhduehdhue"
        val result = userRemoteDataSource.auth(localToken)
        return result.auth;
    }

    override suspend fun registerUser(firstName: String, lastName: String, email: String, password: String): Boolean {
        val result = userRemoteDataSource.register(firstName, lastName, email, password)
        return result.register;
    }
}
