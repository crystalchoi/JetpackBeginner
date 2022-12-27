package com.example.bookshelf.network

import com.example.bookshelf.model.google.GoogleBook
import retrofit2.http.GET


//  https://www.googleapis.com/books/v1/volumes?q=cicero
//  https://www.googleapis.com/books/v1/volumes/<volume_id>

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
}

