package com.example.photogallarycompose

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.photogallarycompose.ui.PhotoCell
import com.example.photogallarycompose.ui.theme.PhotogallaryComposeTheme
import kotlinx.coroutines.*


private const val TAG = "PhotoGalleryMain"
class MainActivity : ComponentActivity() {
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
                    PhotoCell()
                }
            }
        }

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        val job = Job()
//        val scope = CoroutineScope(job)
//        scope.launch {
        lifecycleScope.launch {
            try {
                val response = PhotoRepository().fetchPhotos()
                Log.d(TAG, "Response received: $response")
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
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