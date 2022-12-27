package com.example.bookshelf.data

import com.example.bookshelf.model.google.search.GoogleBook
import com.example.bookshelf.model.google.search.Item
import com.example.bookshelf.model.google.volume.DirectVolume
import com.example.bookshelf.network.BookApiService

interface BookRepository {
//    suspend fun getBooks() : String
    suspend fun getBooks() : GoogleBook
    suspend fun getVolume(id: String) : DirectVolume
}

class DefaultBookRepository(val retrofitService: BookApiService) : BookRepository {
    override suspend fun getBooks(): GoogleBook {
        return retrofitService.getBooks()
    }

    override suspend fun getVolume(id : String): DirectVolume {
        return retrofitService.getVolume(id = id)
    }
}

//class NetworkBookRepository(val bookApiService: BookApiService) : BookRepository {
//    override suspend fun getBooks(): GoogleBook = bookApiService.getBooks()
//}