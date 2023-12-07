package com.example.projet_prog_mobile.data.api.invoice

import com.example.projet_prog_mobile.data.api.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InvoiceAPI {
    @GET("/api/shop/list")
    suspend fun getInvoicesList(): ApiResponse<ListInvoiceResponse>

    @POST("/api/shop/upload")
    suspend fun createInvoice(@Body request: InvoiceRequest): ApiResponse<CreateInvoiceResponse>
}