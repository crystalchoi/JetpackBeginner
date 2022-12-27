package com.example.bookshelf.model.google.volume


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(
    @SerialName("height")
    val height: String = "",
    @SerialName("thickness")
    val thickness: String = "",
    @SerialName("width")
    val width: String = ""
)