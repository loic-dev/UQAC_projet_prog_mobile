package com.example.projet_prog_mobile.data.repository

import com.example.projet_prog_mobile.data.api.ApiException
import com.example.projet_prog_mobile.data.api.user.UserRemoteDataSource
import com.example.projet_prog_mobile.data.local.user.User
import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import com.example.projet_prog_mobile.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.UUID

class UserRepositoryImpl(
        private val userLocalDataSource: UserLocalDataSource,
        private val userRemoteDataSource: UserRemoteDataSource,
        private val ioDispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun authUser(): Boolean {
        return try {
            withContext(ioDispatcher) {
                val userEntity = userLocalDataSource.getUserEntity()
                if (userEntity != null) {
                    userEntity.token?.let { userRemoteDataSource.auth(it) }
                }
            }
            true
        } catch (e: ApiException) {
            false
        }
    }
    override suspend fun loginUser(email:String, password:String): Boolean {
        return try {
            val user =  userRemoteDataSource.login(email,password)
            withContext(ioDispatcher) {
                val currentUser = userLocalDataSource.getUserEntity()
                val userDetail = User(
                    uid = UUID.randomUUID().toString(),
                    token = user.token,
                    firstName = user.firstname,
                    lastName = user.lastname,
                    email=user.email)
                if(currentUser != null){
                    userLocalDataSource.updateUserEntity(userDetail)
                } else {
                    userLocalDataSource.createUserEntity(userDetail)
                }
            }
            true
        } catch (e: ApiException){
            throw e
        }
    }

    override suspend fun registerUser(firstName: String, lastName: String, email: String, password: String): Boolean {
        return try {
            userRemoteDataSource.register(firstName, lastName, email, password)
            true
        } catch (e: ApiException){
            throw e
        }
    }
}
