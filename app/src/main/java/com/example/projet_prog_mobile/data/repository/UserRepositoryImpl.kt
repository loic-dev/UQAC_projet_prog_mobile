package com.example.projet_prog_mobile.data.repository

import android.util.Log
import com.example.projet_prog_mobile.data.api.user.UserRemoteDataSource
import com.example.projet_prog_mobile.data.local.user.User
import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import com.example.projet_prog_mobile.domain.repository.UserRepository
import java.util.UUID

class UserRepositoryImpl(
        private val userLocalDataSource: UserLocalDataSource,
        private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun authUser(): Boolean {
        val token = userLocalDataSource.getUserEntity().token
        var auth = false
        if(token != null){
            auth = userRemoteDataSource.auth(token).auth
        }
        return auth
    }
    override suspend fun loginUser(email:String, password:String): Boolean {
        return try {
            val user =  userRemoteDataSource.login(email,password)
            val userDetail = User(
                uid = UUID.randomUUID().toString(),
                token = user.token,
                firstName = user.firstname,
                lastName = user.lastname,
                email=user.email)
            userLocalDataSource.createUserEntity(userDetail)
            true
        } catch (e: Exception){
            Log.d("test","false")
            false;
        }



    }


}
