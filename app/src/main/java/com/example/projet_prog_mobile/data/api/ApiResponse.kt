package com.example.projet_prog_mobile.data.api

import android.util.Log

data class ApiResponse<T>(
    val code: Int,
    val message: T? = null,
    val error: String?,
)
suspend inline fun <reified T : Any> handleApiCall(call: suspend () -> ApiResponse<T>): T {
    try {
        val response = call.invoke()
        if(response.code == 200){
            return response.message ?: throw Exception("No value")
        } else {
            throw Exception("${response.error}")
        }
    } catch (e: Exception) {
        Log.d("expetionj","$e")
        throw Exception("Unexpected error")
    }
}

