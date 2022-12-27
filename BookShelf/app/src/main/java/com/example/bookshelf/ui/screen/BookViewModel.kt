package com.example.bookshelf.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BookRepository
import com.example.bookshelf.model.google.search.GoogleBook
import com.example.bookshelf.model.google.volume.ImageLinks
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpRetryException

class BookViewModel(private val bookRepository: BookRepository) : ViewModel() {
    var uiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            uiState = try {
                val response = bookRepository.getBooks()
                val  imageList: MutableList<ImageLinks> = emptyList<ImageLinks>().toMutableList()
                runBlocking {
                    response.items.forEach { item ->

                        withContext(Dispatchers.IO) {
                            val volumeResponse = bookRepository.getVolume(item.id)
    //                        Log.d("Volume", "${volumeResponse.id}, ${volumeResponse.volumeInfo.imageLinks.smallThumbnail}")

                            imageList.add(volumeResponse.volumeInfo.imageLinks)
                        }
                    }
                }

                BookUiState.Success(books = response, imageList = imageList)

            } catch(e: IOException) {
                BookUiState.Error
            } catch(e: HttpException) {
                BookUiState.Error
            } catch(e: HttpRetryException) {  //??
                BookUiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookShelfApplication)
                val bookRepository = application.container.bookRepository
                BookViewModel(bookRepository = bookRepository)
            }
        }
    }
}


sealed interface BookUiState {
//    data class Success(val books: String) : BookUiState
    data class Success(val books: GoogleBook, val imageList: List<ImageLinks>) : BookUiState
    object Loading : BookUiState
    object Error : BookUiState
}
