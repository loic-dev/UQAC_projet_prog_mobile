package com.example.projet_prog_mobile.data.api.product


data class ProductItem(
    val title:String,
    val highest_recorded_price:Float,
    val lowest_recorded_price:Float,
)
data class ProductResponse(
    val items:List<ProductItem>
)