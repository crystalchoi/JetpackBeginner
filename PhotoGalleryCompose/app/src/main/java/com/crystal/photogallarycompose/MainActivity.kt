package com.crystal.photogallarycompose

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crystal.photogallarycompose.data.GalleryItem
import com.crystal.photogallarycompose.ui.HomeScreen
import com.crystal.photogallarycompose.ui.PhotoCell
import com.crystal.photogallarycompose.ui.theme.PhotogallaryComposeTheme
import kotlinx.coroutines.*


private const val TAG = "PhotoGalleryMain"
class MainActivity : ComponentActivity() {

    private val viewModel: PhotoGalleryViewModel by viewModels()

    init {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotogallaryComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(viewModel.photos)
                }
            }
        }

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.d(TAG, "repeatOnLifecycle -> STARTED")
                viewModel.galleryItems.collect { items ->
                    Log.d(TAG, "Response received: $items")
                    viewMpdel.photos = items
                }
            }
        }

        return super.onCreateView(name, context, attrs)
    }


    override fun onStart() {


        super.onStart()

    }



    override fun onStop() {
        super.onStop()
        //
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotogallaryComposeTheme {
        Greeting("Android")
    }
}