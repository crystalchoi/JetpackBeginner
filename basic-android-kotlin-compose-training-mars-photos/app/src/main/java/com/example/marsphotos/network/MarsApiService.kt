package com.example.marsphotos.network


import retrofit2.http.GET
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Headers.Companion.toHeaders


interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}


//object MarsApi {
//}