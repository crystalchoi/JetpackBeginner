package com.crystal.photogallery.network

import com.crystal.photogallery.data.FlickrResponse
import retrofit2.http.GET

private const val API_KEY = "51121d8083644241cc0497af060ca2b4"

interface FlickrApi {
    @GET("/")
    suspend fun fetchContents(): String

    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s")
    suspend fun fetchPhotos(): FlickrResponse
}