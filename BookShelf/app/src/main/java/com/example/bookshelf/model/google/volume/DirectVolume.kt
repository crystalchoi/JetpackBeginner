package com.example.bookshelf.model.google.volume


import com.example.bookshelf.model.google.search.AccessInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DirectVolume(
    @SerialName("accessInfo")
    val accessInfo: AccessInfo = AccessInfo(),
    @SerialName("etag")
    val etag: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("kind")
    val kind: String = "",
    @SerialName("saleInfo")
    val saleInfo: SaleInfo = SaleInfo(),
    @SerialName("selfLink")
    val selfLink: String = "",
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo = VolumeInfo()
)