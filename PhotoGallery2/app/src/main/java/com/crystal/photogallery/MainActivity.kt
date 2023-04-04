package com.crystal.photogallery

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crystal.photogallery.data.GalleryItem
import com.crystal.photogallery.ui.theme.PhotoGalleryTheme
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryMain"

class MainActivity : ComponentActivity() {

    private val photoGalleryViewModel: PhotoGalleryViewModel by viewModels()

    init {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var response: List<GalleryItem> = emptyList()

        lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.STARTED) {
               photoGalleryViewModel.galleryItems.collect { items ->
                   Log.d(TAG, "Response received: ${items.size}")
                   response = items
               }
           }
        }

        setContent {
            PhotoGalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting(response)
                    Greeting(photoGalleryViewModel.galleryItems.value)
                }
            }
        }
    }
}


@Composable
fun Greeting(items : List<GalleryItem>) {
    Text("items ${items.size}")
}
//@Composable
//fun Greeting(viewModel: PhotoGalleryViewModel, modifier: Modifier = Modifier) {
//
//    when (viewModel.uiState) {
//        is PhotoGalleryUiState.Loading -> {}
//        is PhotoGalleryUiState.Success -> {  Text(text = "Photo items = ${viewModel.uiState.photos}")}
//        is PhotoGalleryUiState.Error -> {}
//
//    }
//
//}
//

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotoGalleryTheme {
//        Greeting(photoGalleryViewModel)
    }
}