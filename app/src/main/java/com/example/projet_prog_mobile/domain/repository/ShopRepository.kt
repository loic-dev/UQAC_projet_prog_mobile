package com.example.projet_prog_mobile.domain.repository

import com.example.projet_prog_mobile.data.local.product.Product

interface ShopRepository {
    suspend fun getListProduct(): List<Product>?
    suspend fun addProduct(product: Product)
}