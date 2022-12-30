package com.example.bookshelf.model.google.search


import com.example.bookshelf.model.google.volume.ImageLinks
import com.example.bookshelf.model.google.volume.PanelizationSummary
import com.example.bookshelf.model.google.volume.ReadingModes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class VolumeInfo(
    @SerialName("allowAnonLogging")
    val allowAnonLogging: Boolean = false,
    @SerialName("authors")
    val authors: List<String> = listOf(),
    @SerialName("averageRating")
    val averageRating: Float? = 0.0f,
    @SerialName("canonicalVolumeLink")
    val canonicalVolumeLink: String = "",
    @SerialName("categories")
    val categories: List<String>? = null,
    @SerialName("contentVersion")
    val contentVersion: String = "",
    @SerialName("description")
    val description: String? = null,
    @SerialName("imageLinks")
    val imageLinks: ImageLinks = ImageLinks(),
    @SerialName("industryIdentifiers")
    val industryIdentifiers: List<IndustryIdentifier> = listOf(),
    @SerialName("infoLink")
    val infoLink: String = "",
    @SerialName("language")
    val language: String = "",
    @SerialName("maturityRating")
    val maturityRating: String =  "",
    @SerialName("pageCount")
    val pageCount: Int? = 0,
    @SerialName("panelizationSummary")
    val panelizationSummary: PanelizationSummary = PanelizationSummary(),
    @SerialName("previewLink")
    val previewLink: String = "",
    @SerialName("printType")
    val printType: String = "",
    @SerialName("publishedDate")
    val publishedDate: String = "",
    @SerialName("publisher")
    val publisher: String? = null,
    @SerialName("ratingsCount")
    val ratingsCount: Int? = 0,
    @SerialName("readingModes")
    val readingModes: ReadingModes = ReadingModes(),
    @SerialName("subtitle")
    val subtitle: String? = null,
    @SerialName("title")
    val title: String = ""
)