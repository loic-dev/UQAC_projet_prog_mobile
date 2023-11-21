package com.example.projet_prog_mobile.data.api

data class ApiResponse<T>(
    val code: Int,
    val message: T? = null,
    val error: String?,
)
inline fun <reified T : Any> handleApiCall(call: () -> ApiResponse<T>): T {
    try {
        val response = call.invoke()
        if(response.code == 200){
            return response.message ?: throw ApiException("No value")
        } else {
            throw ApiException("${response.error}")
        }
    } catch (e: Exception) {
        throw ApiException("Unexpected error")
    }
}

class ApiException(message: String) : Exception(message)


