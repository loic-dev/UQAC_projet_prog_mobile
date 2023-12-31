package com.example.projet_prog_mobile.data.local.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

import androidx.room.Query


@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getListProduct(): List<Product>
    @Query("DELETE FROM Product")
    fun clearShop()

    @Insert
    fun addProduct(vararg product: Product)

    @Delete
    fun deleteProduct(vararg product: Product)
}