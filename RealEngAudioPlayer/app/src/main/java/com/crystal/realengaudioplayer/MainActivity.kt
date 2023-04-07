package com.crystal.realengaudioplayer

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.crystal.realengaudioplayer.ui.screen.PlaySampleAudio
import com.crystal.realengaudioplayer.ui.theme.RealEngAudioPlayerTheme
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.RenderersFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector

class MainActivity : ComponentActivity() {

    private lateinit var exoPlayer: ExoPlayer

    init {
        initializeExoPlayer()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealEngAudioPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PlaySampleAudio(LocalContext.current)
                }
            }
        }
    }


    private fun initializeExoPlayer() {

        val renderersFactory = buildRenderersFactory(applicationContext, true)  // 1
        val trackSelector = DefaultTrackSelector(applicationContext)  // 2
        exoPlayer = ExoPlayer.Builder(applicationContext, renderersFactory)  // 3
            .setTrackSelector(trackSelector)
            .build().apply {
                trackSelectionParameters =
                    DefaultTrackSelector.Parameters.Builder(applicationContext).build()  // 4
                addListener(exoPlayerListener)  // 5
                playWhenReady = false  // 6
            }
    }

    private fun buildRenderersFactory(
        context: Context, preferExtensionRenderer: Boolean
    ): RenderersFactory {
        val extensionRendererMode = if (preferExtensionRenderer)
            DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER
        else DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON

        return DefaultRenderersFactory(context.applicationContext)
            .setExtensionRendererMode(extensionRendererMode)
            .setEnableDecoderFallback(true)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RealEngAudioPlayerTheme {
         PlaySampleAudio(LocalContext.current)
    }
}