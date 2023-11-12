package com.example.projet_prog_mobile.data.api.user

import com.example.projet_prog_mobile.data.api.ApiResponse
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi,
)  {
    suspend fun auth(localToken: String): ApiResponse<UserAuthResponse> {
        return try {
            val response = userApi.authUser(localToken)
            if (response is ApiResponse.Success) {
                ApiResponse.Success(UserAuthResponse(auth = response.data.auth))
            } else {
                response
            }
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }
}
