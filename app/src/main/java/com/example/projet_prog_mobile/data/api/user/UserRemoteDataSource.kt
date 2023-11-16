package com.example.projet_prog_mobile.data.api.user

import com.example.projet_prog_mobile.data.api.handleApiCall
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi,
)  {
    suspend fun auth(localToken: String): UserAuthResponse{
        return handleApiCall { userApi.authUser(localToken) }
    }

    suspend fun login(email:String, password:String): UserLoginResponse{
        return handleApiCall { userApi.loginUser(LoginRequest(email,password)) }
    }
}
