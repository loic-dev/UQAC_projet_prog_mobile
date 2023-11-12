package com.example.projet_prog_mobile.data.api.user

import com.example.projet_prog_mobile.data.api.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {
    @GET("/auth")
    suspend fun authUser(@Header("Authorization") token: String): ApiResponse<UserAuthResponse>
}
