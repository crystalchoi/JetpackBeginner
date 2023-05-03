package com.example.exoplayermedia3.data.datasource

import android.net.Uri


object DummySpotlightData {
    val spotlight = listOf(
//        Spotlight(
//            id = 1,
//            video = "icecream.mp4",
//            userImage = "https://generated.photos/vue-static/home/hero/4.png",
//            userName = "Farhan Roy",
//            isLiked = true,
//            likesCount = 778,
//            commentsCount = 156,
//            comment = "Wkwkwk..."
//        ),
//        Spotlight(
//            id = 2,
//            video = "food.mp4",
//            userImage = "https://generated.photos/vue-static/home/hero/7.png",
//            userName = "Muhammad Ali",
//            isLiked = true,
//            likesCount = 5923,
//            commentsCount = 11,
//            comment = "Awas kamu ya klo pergi"
//        ),
//        Spotlight(
//            id = 3,
//            video = "soap-bubbles.mp4",
//            userImage = "https://generated.photos/vue-static/home/hero/3.png",
//            userName = "Christian Juned",
//            isLiked = true,
//            likesCount = 2314,
//            comment = "Es krim dingin sedapp",
//            commentsCount = 200
//        ),
//        Spotlight(
//            id = 4,
//            video = "lake.mp4",
//            userImage = "https://generated.photos/vue-static/home/hero/6.png",
//            userName = "Cak Jhon",
//            isLiked = true,
//            likesCount = 786,
//            comment = "Ados slurr",
//            commentsCount = 700
//        ),
        Spotlight(
            id = 5,
            video = "castle.mp4",
            userImage = "https://generated.photos/vue-static/home/hero/2.png",
            userName = "David Dulkader",
            isLiked = true,
            likesCount = 1890,
            comment = "Kerjaan di tengah hutan",
            commentsCount = 232
        ),
        Spotlight(
            id = 6,
            video = "castle.mp4",
            userImage = "https://generated.photos/vue-static/home/hero/2.png",
            userName = "David Dulkader",
            isLiked = true,
            likesCount = 1890,
            comment = "Kerjaan di tengah hutan",
            commentsCount = 232
        )

    )
}

data class Spotlight(
    val id: Int,
    private val video: String,
    val userImage: String,
    val userName: String,
    val isLiked: Boolean = false,
    val likesCount: Int,
    val comment: String,
    val commentsCount: Int
) {
    /**
     * Get video url
     *
     * @return
     */
    fun getVideoUrl(): Uri {
        return Uri.parse("asset:///$video")
    }
    fun getAudioUrl(): Uri {
        return Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
    }
}