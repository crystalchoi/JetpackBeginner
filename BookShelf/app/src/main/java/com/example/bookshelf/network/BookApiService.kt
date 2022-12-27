package com.example.bookshelf.network

import com.example.bookshelf.model.google.search.GoogleBook
import com.example.bookshelf.model.google.search.Item
import com.example.bookshelf.model.google.volume.DirectVolume
import retrofit2.http.GET
import retrofit2.http.Path


//  https://www.googleapis.com/books/v1/volumes?q=cicero
//  https://www.googleapis.com/books/v1/volumes/<volume_id>
//  https://www.googleapis.com/books/v1/volumes/022xAAAAIAAJ

// In "volumeInfo"
// "imageLinks": {
//    "smallThumbnail": "http://books.google.com/books/content?id=mNvL-eJMUgAC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
//    "thumbnail": "http://books.google.com/books/content?id=mNvL-eJMUgAC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
//},



interface BookApiService {
    @GET("v1/volumes?q=cicero")
//    @GET("amphibians")
//    suspend fun getBooks() : String
    suspend fun getBooks() : GoogleBook

    @GET("v1/volumes/{volume_id}")
    suspend fun getVolume(@Path("volume_id") id: String) : DirectVolume
}

