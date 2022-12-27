package com.example.bookshelf.model.google


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaleInfo(
    @SerialName("country")
    val country: String,
    @SerialName("isEbook")
    val isEbook: Boolean,
    @SerialName("saleability")
    val saleability: String
)