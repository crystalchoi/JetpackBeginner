package com.example.bookshelf.model.google


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pdf(
    @SerialName("acsTokenLink")
    val acsTokenLink: String? = null,
    @SerialName("isAvailable")
    val isAvailable: Boolean
)