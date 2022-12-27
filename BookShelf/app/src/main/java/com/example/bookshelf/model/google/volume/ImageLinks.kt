package com.example.bookshelf.model.google.volume


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageLinks(
    @SerialName("smallThumbnail")
    val smallThumbnail: String = "",
    @SerialName("thumbnail")
    val thumbnail: String = "",
    @SerialName("small")
    val small: String = "",
    @SerialName("medium")
    val medium: String = "",
    @SerialName("large")
    val large: String = "",
    @SerialName("extraLarge")
    val extraLarge: String = ""
)