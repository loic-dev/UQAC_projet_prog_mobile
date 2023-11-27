package com.example.projet_prog_mobile.data.api.user

import com.example.projet_prog_mobile.data.api.handleApiCall
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi,
)  {
    suspend fun auth(localToken: String): String {
        return handleApiCall { userApi.authUser("Bearer $localToken") }
    }

    suspend fun login(email:String, password:String): UserLoginResponse{
        return handleApiCall { userApi.loginUser(LoginRequest(email,password)) }
    }

    suspend fun register(firstName: String, lastName: String, email: String, password: String): String{
        return handleApiCall { userApi.registerUser(RegisterRequest(firstName, lastName, email, password)) }
    }
}
