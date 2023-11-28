package com.example.projet_prog_mobile.data.api

import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val modifiedRequest = when {
            shouldAddToken(request) -> {
                val token = userLocalDataSource.getUserEntity().token
                if (token != null) {
                    request.newBuilder()
                        .header("Authorization", "Bearer $token")
                        .build()
                } else {
                    request
                }
            }
            else -> request
        }

        return chain.proceed(modifiedRequest)
    }

    private fun shouldAddToken(request: Request): Boolean {
        return request.url().toString().startsWith("http://192.168.1.111:8000/api")
    }
}