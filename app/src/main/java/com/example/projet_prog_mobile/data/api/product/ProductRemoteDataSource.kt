package com.example.projet_prog_mobile.data.api.product

import com.example.projet_prog_mobile.data.api.handleApiCall
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val productAPI: ProductApi
) {
    suspend fun getProductByUpc(upc: String): ProductResponse {
        return handleApiCall { productAPI.getProductByUpc(upc) }
    }
}