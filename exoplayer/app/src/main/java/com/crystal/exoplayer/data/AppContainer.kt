package com.crystal.exoplayer.data

import com.crystal.exoplayer.network.EpisodeApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlinx.serialization.json.Json


interface AppContainer {
    val progressRepository : ProgressRepository
}

class DefaultAppContainer : AppContainer {

//    private val BASE_URL = "https://v1.wisdomhouse.co.kr/mp3/realenglish/"
    private val BASE_URL = "http://books.google.com/books/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()


    private val retrofitService : EpisodeApiService by lazy {
        retrofit.create(EpisodeApiService::class.java)
    }

    override val progressRepository: ProgressRepository by lazy {
        DefaultProgressRepository(retrofitService)
    }


}