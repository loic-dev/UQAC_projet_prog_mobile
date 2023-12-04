package com.example.projet_prog_mobile.presentation.state

import com.example.projet_prog_mobile.data.local.product.Product


data class ShopState(
    val products: List<Product> = listOf()
)
