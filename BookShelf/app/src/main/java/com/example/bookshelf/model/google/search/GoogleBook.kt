package com.example.bookshelf.model.google.search


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleBook(
    @SerialName("items")
    val items: List<Item> = listOf(),
    @SerialName("kind")
    val kind: String = "",
    @SerialName("totalItems")
    val totalItems: Int = 0
)