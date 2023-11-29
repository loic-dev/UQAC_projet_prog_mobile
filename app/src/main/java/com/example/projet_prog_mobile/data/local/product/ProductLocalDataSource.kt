package com.example.projet_prog_mobile.data.local.product

import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(
    private val productDao: ProductDao
) {
    fun getListProduct(): List<Product> {
        return productDao.getListProduct()
    }

    fun addProduct(product: Product){
        return productDao.addProduct(product)
    }
}