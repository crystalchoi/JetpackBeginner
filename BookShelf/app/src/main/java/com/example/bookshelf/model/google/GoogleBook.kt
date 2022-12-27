package com.example.bookshelf.model.google


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleBook(
    @SerialName("items")
    val items: List<Item>,
    @SerialName("kind")
    val kind: String,
    @SerialName("totalItems")
    val totalItems: Int
)