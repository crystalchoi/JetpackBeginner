package com.example.marsphotos.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Headers.Companion.toHeaders


interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}

