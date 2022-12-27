package com.example.bookshelf.data

import com.example.bookshelf.model.google.GoogleBook
import com.example.bookshelf.network.BookApiService

interface BookRepository {
//    suspend fun getBooks() : String
    suspend fun getBooks() : GoogleBook
}

class DefaultBookRepository(val retrofitService: BookApiService) : BookRepository {
//    override suspend fun getBooks(): String {
    override suspend fun getBooks(): GoogleBook {
        return retrofitService.getBooks()
    }
}

class NetworkBookRepository(val bookApiService: BookApiService) : BookRepository {
//    override suspend fun getBooks(): String = bookApiService.getBooks()
    override suspend fun getBooks(): GoogleBook = bookApiService.getBooks()
}