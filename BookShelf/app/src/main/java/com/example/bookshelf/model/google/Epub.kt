package com.example.bookshelf.model.google


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Epub(
    @SerialName("isAvailable")
    val isAvailable: Boolean
)