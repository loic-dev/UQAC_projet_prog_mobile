package com.example.projet_prog_mobile.domain.repository

import com.example.projet_prog_mobile.data.local.product.Product

interface ShopRepository {
    suspend fun getListProduct(): List<Product>
    suspend fun addProduct(product: Product)

    suspend fun createInvoice():Boolean

    suspend fun clearShop()
    suspend fun deleteProduct(product: Product)
}