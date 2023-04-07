package com.crystal.realengaudioplayer.data

class RealEngEpisode {
    private val urlHeader = "https://v1.wisdomhouse.co.kr/mp3/realenglish/"   // + realenglish001.mp3

    fun getUrl(episodeNum: Int) : String = urlHeader.plus("realenglilsh")
                                            .plus(String.format("%d", episodeNum))
                                            .plus(".mp3")
}