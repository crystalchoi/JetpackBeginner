package com.example.photpgallarycompose

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
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
import com.example.photpgallarycompose.api.FlickrApi
import com.example.photpgallarycompose.ui.PhotoCell
import com.example.photpgallarycompose.ui.theme.PhotpgallaryComposeTheme
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create


private const val TAG = "PhotoGalleryMain"
class MainActivity : ComponentActivity() {

    init {
//        val job = Job()
//        val scope = CoroutineScope(job)
//    scope.launch {
        lifecycleScope.launch() {
//        runBlocking {
            val response = PhotoRepository().fetchPhotos()
//            val response = PhotoRepository().fetchContents()
            Log.d(TAG, "Response received: $response")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotpgallaryComposeTheme {
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
    PhotpgallaryComposeTheme {
        Greeting("Android")
    }
}