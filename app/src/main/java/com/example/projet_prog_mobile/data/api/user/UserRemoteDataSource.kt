package com.example.projet_prog_mobile.data.api.user

import com.example.projet_prog_mobile.data.api.handleApiCall
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi,
)  {
    suspend fun auth(localToken: String): UserAuthResponse{
        return handleApiCall { userApi.authUser(localToken) }
    }
}
