package com.crystal.photogallarycompose.api

import com.crystal.photogallarycompose.data.FlickrResponse
import retrofit2.http.GET


private const val API_KEY = "51121d8083644241cc0497af060ca2b4"

// https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=yourApiKeyHere&format=json&nojsoncallback=1&extras=url_s
// https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=51121d8083644241cc0497af060ca2b4&format=json&nojsoncallback=1&extras=url_s

interface FlickrApi {
    @GET("services/rest/?method=flickr.interestingness.getList"
        + "&api_key=$API_KEY" + "&format=json"
        + "&nojsoncallback=1"
        + "&extras=url_s" + "&date=2023-03-30"
    )
//    @GET("services/rest/?method=flickr.interestingness.getList")
    suspend fun fetchPhotos(): FlickrResponse


    @GET("/")
    suspend fun fetchContents(): String
}