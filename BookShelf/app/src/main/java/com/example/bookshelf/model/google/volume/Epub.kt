package com.example.bookshelf.model.google.volume


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Epub(
    @SerialName("isAvailable")
    val isAvailable: Boolean = false
)