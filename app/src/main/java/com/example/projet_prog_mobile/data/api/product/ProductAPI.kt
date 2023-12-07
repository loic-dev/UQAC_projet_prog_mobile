package com.example.projet_prog_mobile.data.api.product

import com.example.projet_prog_mobile.data.api.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("/api/lookup/{upc}")
    suspend fun getProductByUpc(
        @Path("upc") upc: String
    ): ApiResponse<ProductResponse>
}
