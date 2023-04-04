package com.crystal.photogallery

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crystal.photogallery.data.GalleryItem
import com.crystal.photogallery.network.PhotoGalleryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {

    var uiState: PhotoGalleryUiState by mutableStateOf(PhotoGalleryUiState.Loading)
        private set

    private val photoRepository = PhotoGalleryRepository()

    val _galleryItems: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            uiState = PhotoGalleryUiState.Loading
            try {
                val items = photoRepository.fetchPhotos()
                Log.d(TAG, "Items received: $items")
                _galleryItems.value = items
                uiState = PhotoGalleryUiState.Success(photos = _galleryItems.value)
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
                uiState = PhotoGalleryUiState.Error
            }
        }
    }
}


sealed interface PhotoGalleryUiState {
    data class Success(val photos: List<GalleryItem>) : PhotoGalleryUiState
    object Loading : PhotoGalleryUiState
    object Error : PhotoGalleryUiState
}
