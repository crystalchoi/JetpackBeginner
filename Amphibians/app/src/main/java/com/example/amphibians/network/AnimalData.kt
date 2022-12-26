package com.example.amphibians.network

import kotlinx.serialization.SerialName
import java.io.FileDescriptor

//{
//    "name": "Great Basin Spadefoot",
//    "type": "Toad",
//    "description": "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
//    "img_src": "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
//}

@kotlinx.serialization.Serializable
data class AnimalData(val name: String
    , val type: String
    , val description: String
    , @SerialName(value = "img_src") val imgSrc: String
)
