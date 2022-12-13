package com.example.photogallerypoview.api

import retrofit2.http.GET
import retrofit2.http.Query



//     interestingness 카테고리의 사진만 긁어오기
//  https://api.flickr.com/services/rest/?method=flickr.interestingness.getList
//     &api_key=yourApiKeyHere
//     &format=json
//     &nojsoncallback=1
//     &extras=url_s

//      cat 으로 검색했을때, query
// https://api.flickr.com/services/rest/?method=flickr.photos.search
//      &api_key=xxx
//      &format=json
//      &nojsoncallback=1
//      &extras=url_s
//      &safe_search=1    // ?? filter for potentially offensive results
//      &text=cat

interface FlickrApi {
//    @GET("/")
//    suspend fun fetchContents() : String

//    @GET(
//        "services/rest/?method=flickr.interestingness.getList" +
//                "&api_key=$API_KEY" +
//                "&format=json" +
//                "&nojsoncallback=1" +
//                "&extras=url_s"
//    )
    @GET("services/rest/?method=flickr.interestingness.getList")
    suspend fun fetchPhotos() : FlickrResponse

    @GET("services/rest/?method=flickr.photos.search")
    suspend fun searchPhotos(@Query("text") query: String) : FlickrResponse
}