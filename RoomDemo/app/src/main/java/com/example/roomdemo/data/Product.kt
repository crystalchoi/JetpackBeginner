package com.example.roomdemo.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "productId")
    var id: Int = 0,
    @ColumnInfo(name = "productName")
    var productName: String = "",
    var quantity: Int = 0,

//            constructor()
//    constructor(productname: String, quantity: Int) {
//        this.productName = productname
//        this.quantity = quantity
//    }
)
