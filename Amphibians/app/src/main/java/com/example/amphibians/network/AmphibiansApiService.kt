package com.example.amphibians.network

import retrofit2.http.GET


// https://android-kotlin-fun-mars-server.appspot.com/amphibians

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getInfos() :  List<AnimalData>
}


