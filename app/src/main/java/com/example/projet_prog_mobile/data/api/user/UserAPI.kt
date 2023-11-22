package com.example.projet_prog_mobile.data.api.user

import com.example.projet_prog_mobile.data.api.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {

    @GET("/api")
    suspend fun authUser(@Header("Authorization") token: String): ApiResponse<String>

    @POST("/register")
    suspend fun registerUser(@Body request: RegisterRequest): ApiResponse<String>

    @POST("/login")
    suspend fun loginUser(@Body request: LoginRequest): ApiResponse<UserLoginResponse>

}
