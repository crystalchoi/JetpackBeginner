package com.example.bookshelf.network

import retrofit2.http.GET


//  https://www.googleapis.com/books/v1/volumes?q=cicero
//  https://www.googleapis.com/books/v1/volumes/<volume_id>

// In "volumeInfo"
// "imageLinks": {
//    "smallThumbnail": "http://books.google.com/books/content?id=mNvL-eJMUgAC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
//    "thumbnail": "http://books.google.com/books/content?id=mNvL-eJMUgAC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
//},

private const val BASE_URL = "https://www.googleapis.com/books"

interface GoogleBooksApiService() {
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}

