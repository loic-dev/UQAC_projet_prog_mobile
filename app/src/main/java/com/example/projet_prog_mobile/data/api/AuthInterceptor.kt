package com.example.projet_prog_mobile.data.api

import android.content.Context
import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import com.example.projet_prog_mobile.R

class AuthInterceptor @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val modifiedRequest = when {
            shouldAddToken(request) -> {
                val user = userLocalDataSource.getUserEntity()
                if (user != null) {
                    val token = user.token
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
        val apiUrl = context.getString(R.string.api_url)+"/api"
        return request.url.toString().startsWith(apiUrl)
    }
}