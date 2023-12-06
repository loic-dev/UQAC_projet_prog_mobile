package com.example.projet_prog_mobile.data.repository

import com.example.projet_prog_mobile.data.api.ApiException
import com.example.projet_prog_mobile.data.api.user.UserRemoteDataSource
import com.example.projet_prog_mobile.data.local.user.User
import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import com.example.projet_prog_mobile.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
        private val userLocalDataSource: UserLocalDataSource,
        private val userRemoteDataSource: UserRemoteDataSource,
        private val ioDispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun authUser(): Boolean {
        return try {
            userRemoteDataSource.auth()
            true
        } catch (e: ApiException) {
            false
        }
    }
    override suspend fun loginUser(email:String, password:String): Boolean {
        return try {
            val loginResponse =  userRemoteDataSource.login(email,password)
            withContext(ioDispatcher) {
                val currentUser = userLocalDataSource.getUserEntity()
                if(currentUser != null){
                    val userUpdated = currentUser.copy(token = currentUser.token)
                    userLocalDataSource.updateUserEntity(userUpdated)
                } else {
                    val userDetail = User(
                        uid = UUID.randomUUID().toString(),
                        token = loginResponse.token,
                        firstName = loginResponse.user.firstname,
                        lastName = loginResponse.user.lastname,
                        email=loginResponse.user.email)
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

    override suspend fun getUser(): User? {
        return try {
            withContext(ioDispatcher) {
                userLocalDataSource.getUserEntity()
            }
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun logout() {
        return try {
            withContext(ioDispatcher) {
                userLocalDataSource.deleteUserEntity()
            }
        } catch (e: Exception){
            throw e
        }
    }
}
