package com.example.bookshelf.model.google


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadingModes(
    @SerialName("image")
    val image: Boolean,
    @SerialName("text")
    val text: Boolean
)