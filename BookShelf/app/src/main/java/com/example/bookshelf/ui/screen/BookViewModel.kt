package com.example.bookshelf.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.bookshelf.network.GoogleBooksApiService

class BookViewModel {
    var uiState:String by mutableStateOf("")
        private set

    init {
        getBooks()
    }

    private fun getBooks() : String {
//        val listResult = GoogleBooksApiService.retrofit.getBooks()
        uiState = "Set the Mars API status response here!"
    }
}