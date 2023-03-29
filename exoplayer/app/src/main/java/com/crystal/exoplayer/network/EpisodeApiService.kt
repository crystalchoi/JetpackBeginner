package com.crystal.exoplayer.network


import retrofit2.http.GET


// https://v1.wisdomhouse.co.kr/mp3/realenglish/
// https://v1.wisdomhouse.co.kr/mp3/realenglish/realenglish001.mp3


interface EpisodeApiService {
//    @GET("/")
//    suspend fun getInfos() :  List<EpisodeData>

    suspend fun getInfos() :  List<EpisodeData>  {
        return listOf(EpisodeData(name = "name", type= "type", description = "desc"), )
    }
}


