package com.crystal.photogallarycompose

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crystal.photogallarycompose.PhotoRepository
import com.crystal.photogallarycompose.data.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"
class PhotoGalleryViewModel : ViewModel() {
    var uiState: PhotoGalleryUiState by mutableStateOf(PhotoGalleryUiState.Loading)
        private set

    private val photoRepository = PhotoRepository()
    private val _galleryItems: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

//    var  photos: List<GalleryItem> = emptyList()

    init {
        getPhotos()
    }

    fun getPhotos() : PhotoGalleryUiState {
        viewModelScope.launch {
            uiState = PhotoGalleryUiState.Loading
            uiState = try {
//                Log.d(TAG, "viewModel -> Launch")
//                val items = photoRepository.fetchPhotos()
//                Log.d(TAG, "Items received: $items")
//                _galleryItems.value = items
                PhotoGalleryUiState.Success(photoRepository.fetchPhotos())
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
                PhotoGalleryUiState.Error
            }

        }
    }


}


sealed interface PhotoGalleryUiState {
    data class Success(val photos: List<GalleryItem>) : PhotoGalleryUiState
    object Loading : PhotoGalleryUiState
    object Error : PhotoGalleryUiState
}
