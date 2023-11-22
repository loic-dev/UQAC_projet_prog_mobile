package com.example.projet_prog_mobile.data.api

import android.util.Log

data class ApiResponse<T>(
    val code: Int,
    val message: T? = null,
    val error: String?,
)
inline fun <reified T : Any> handleApiCall(call: () -> ApiResponse<T>): T {
    try {
        val response = call.invoke()
        Log.d("response", response.toString())
        if(response.code in 200 until 300){
            return response.message ?: throw ApiException("No value")
        } else {
            throw ApiException("${response.error}")
        }
    } catch (e: Exception) {
        Log.d("error", e.toString())
        throw ApiException("Unexpected error")
    }
}

class ApiException(message: String) : Exception(message)


