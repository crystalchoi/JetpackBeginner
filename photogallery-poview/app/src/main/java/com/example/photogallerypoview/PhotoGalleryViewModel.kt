package com.example.photogallerypoview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photogallerypoview.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {

    private val photoRepository = PhotoRepository()

    private val _galleryItems : MutableStateFlow<List<GalleryItem>>
          = MutableStateFlow(emptyList())

    val galleryItem: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    init {
        viewModelScope.launch {
            try {
//                val items = photoRepository.fetchPhotos()
//                val items = photoRepository.searchPhotos("llama")
                val items = fetchGalleryItems("planets")
                Log.d(TAG, "response: ${items.size}")
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
            }
        }
    }

    fun setQuery(query: String) {
        viewModelScope.launch {
            Log.d(TAG, "viewModelScope.launch")
            val items = fetchGalleryItems(query)
            Log.d(TAG, "setQuery: response: ${items.size}")
            _galleryItems.value = items
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