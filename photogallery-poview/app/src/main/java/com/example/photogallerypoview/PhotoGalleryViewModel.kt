package com.example.photogallerypoview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photogallerypoview.api.GalleryItem
import com.example.photogallerypoview.model.PhotoRepository
import com.example.photogallerypoview.model.PreferencesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {

    private val photoRepository = PhotoRepository()
    private val preferencesRepository = PreferencesRepository.get()

//    private val _galleryItems : MutableStateFlow<List<GalleryItem>>
//          = MutableStateFlow(emptyList())

//    val galleryItem: StateFlow<List<GalleryItem>>
//        get() = _galleryItems.asStateFlow()

    private val _uiState: MutableStateFlow<PhotoGalleryUiState> = MutableStateFlow(PhotoGalleryUiState())
    val uiState: StateFlow<PhotoGalleryUiState>
        get() = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            preferencesRepository.storedQuery.collectLatest { storedQuery ->
                try {
    //                val items = photoRepository.fetchPhotos()
    //                val items = photoRepository.searchPhotos("llama")
                    val items = fetchGalleryItems(storedQuery)
                    Log.d(TAG, "response: ${items.size}")
                    _uiState.update { oldState ->
                        oldState.copy(images = items, query = storedQuery)
                    }

                } catch (ex: Exception) {
                    Log.e(TAG, "Failed to fetch gallery items", ex)
                }
            }
        }
    }

    fun setQuery(query: String) {
        viewModelScope.launch {
//            Log.d(TAG, "viewModelScope.launch")
//            val items = fetchGalleryItems(query)
//            Log.d(TAG, "setQuery: response: ${items.size}")
//            _galleryItems.value = items
            preferencesRepository.setStoreQuery(query)
        }
    }

    private suspend fun fetchGalleryItems(query: String) : List<GalleryItem> {
        return if (query.isNotEmpty()) {
            photoRepository.searchPhotos(query)
        } else {
            photoRepository.fetchPhotos()
        }
    }
}


data class PhotoGalleryUiState(
    val images: List<GalleryItem> = listOf(),
    val query: String = ""
)