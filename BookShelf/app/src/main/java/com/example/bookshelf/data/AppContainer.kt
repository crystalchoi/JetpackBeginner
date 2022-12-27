package com.example.bookshelf.data

import com.example.bookshelf.network.BookApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val bookRepository : BookRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL = "https://www.googleapis.com/books/"
//    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"


    @OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }

    override val bookRepository: BookRepository by lazy {
        DefaultBookRepository(retrofitService = retrofitService)
    }
}