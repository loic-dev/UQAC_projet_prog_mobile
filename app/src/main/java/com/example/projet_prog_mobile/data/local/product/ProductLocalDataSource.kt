package com.example.projet_prog_mobile.data.local.product

import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(
    private val productDao: ProductDao
) {
    fun getListProduct(): List<Product> {
        return productDao.getListProduct()
    }

    fun clearShop() {
        return productDao.clearShop()
    }



    fun addProduct(product: Product){
        return productDao.addProduct(product)
    }

    fun deleteProduct(product: Product){
        return productDao.deleteProduct(product)
    }
}