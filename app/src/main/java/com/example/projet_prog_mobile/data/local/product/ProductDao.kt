package com.example.projet_prog_mobile.data.local.product

import androidx.room.Dao
import androidx.room.Insert

import androidx.room.Query


@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getListProduct(): List<Product>

    @Insert
    fun addProduct(vararg product: Product)
}