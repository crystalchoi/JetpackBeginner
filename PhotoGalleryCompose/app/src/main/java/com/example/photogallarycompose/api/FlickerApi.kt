package com.example.photogallarycompose.api

import com.example.photogallarycompose.data.FlickrResponse
import retrofit2.http.GET


private const val API_KEY = "97be5666ff4dcfe499ed19a7a5e06c5e"

// https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=yourApiKeyHere&format=json&nojsoncallback=1&extras=url_s
// https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=97be5666ff4dcfe499ed19a7a5e06c5e&format=json&nojsoncallback=1&extras=url_s

interface FlickrApi {
    @GET("services/rest/?method=flickr.interestingness.getList"
        + "&api_key=$API_KEY" + "&format=json"
        + "&nojsoncallback=1"
        + "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse


    @GET("/")
    suspend fun fetchContents(): String
}