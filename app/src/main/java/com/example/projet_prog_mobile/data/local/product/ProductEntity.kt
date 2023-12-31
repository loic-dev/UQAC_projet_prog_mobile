package com.example.projet_prog_mobile.data.local.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image: String? = null,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
)