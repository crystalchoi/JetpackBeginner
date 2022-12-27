package com.example.bookshelf.ui.screen

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
import com.example.bookshelf.model.google.GoogleBook
import kotlinx.coroutines.launch
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
//        uiState = "Set the Mars API status response here!"
        viewModelScope.launch {
            uiState = try {
//                val result = bookRepository.getBooks()
//                uiState = "${result.kind} $result.totalItems ${result.items.size}"
                    BookUiState.Success(bookRepository.getBooks())
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
    data class Success(val books: GoogleBook) : BookUiState
    object Loading : BookUiState
    object Error : BookUiState
}
