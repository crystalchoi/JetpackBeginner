package com.example.bookshelf.model.google.volume


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    @SerialName("allowAnonLogging")
    val allowAnonLogging: Boolean = false,
    @SerialName("authors")
    val authors: List<String> = listOf(),
    @SerialName("averageRating")
    val averageRating: Int = 0,
    @SerialName("canonicalVolumeLink")
    val canonicalVolumeLink: String = "",
    @SerialName("categories")
    val categories: List<String> = listOf(),
    @SerialName("contentVersion")
    val contentVersion: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("dimensions")
    val dimensions: Dimensions = Dimensions(),
    @SerialName("imageLinks")
    val imageLinks: ImageLinks = ImageLinks(),
    @SerialName("industryIdentifiers")
    val industryIdentifiers: List<IndustryIdentifier> = listOf(),
    @SerialName("infoLink")
    val infoLink: String = "",
    @SerialName("language")
    val language: String = "",
    @SerialName("maturityRating")
    val maturityRating: String = "",
    @SerialName("pageCount")
    val pageCount: Int = 0,
    @SerialName("panelizationSummary")
    val panelizationSummary: PanelizationSummary = PanelizationSummary(),
    @SerialName("previewLink")
    val previewLink: String = "",
    @SerialName("printType")
    val printType: String = "",
    @SerialName("printedPageCount")
    val printedPageCount: Int = 0,
    @SerialName("publishedDate")
    val publishedDate: String = "",
    @SerialName("publisher")
    val publisher: String = "",
    @SerialName("ratingsCount")
    val ratingsCount: Int = 0,
    @SerialName("readingModes")
    val readingModes: ReadingModes = ReadingModes(),
    @SerialName("title")
    val title: String = ""
)