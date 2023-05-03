package com.example.exoplayermedia3


import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exoplayermedia3.data.datasource.DummySpotlightData
import com.example.exoplayermedia3.ui.theme.ExoPlayerMedia3Theme
import androidx.compose.ui.res.stringResource
import com.example.exoplayermedia3.R

val horizontalPadding = 10.dp
@Composable
fun SpotlightScreen() {
    val spotlights = DummySpotlightData.spotlight
    Box(
        Modifier
            .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
            .background(color = Color.Black)
    ) {
        LazyColumn {
            items(spotlights.size) { index ->
                Box(
                    modifier = Modifier
                        .fillParentMaxSize()
                ) {
//                    VideoPlayer(uri = spotlights[index].getVideoUrl())
                    VideoPlayer(uri = spotlights[index].getAudioUrl())
                    Column(Modifier.align(Alignment.BottomStart)) {
                        //SpotlightFooter(spotlights[index])
                        Divider()
                    }
                }
            }
        }
    }
}


/**
 * Video player
 *
 * @param uri
 */
@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun VideoPlayer(uri: Uri) {
    val context = LocalContext.current

    var currentItemState: Int  by remember { mutableStateOf(0) }
    var playbackPositionState: Long  by remember { mutableStateOf(0L) }
    var playWhenReadyState: Boolean  by remember { mutableStateOf(true) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
//            .apply {
//                val defaultDataSourceFactory = DefaultDataSource.Factory(context)
//                val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
//                    context,
//                    defaultDataSourceFactory
//                )
//                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
//                    .createMediaSource(MediaItem.fromUri(uri))
//
//                setMediaSource(source)
//                prepare()
//            }   // static video file
            .apply {
//                val mediaItem = MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
                val mediaItem = MediaItem.fromUri(uri)
                setMediaItem(mediaItem)

//                playWhenReady = exoPlayer.playWhenReady
                playWhenReadyState = playWhenReady
                currentItemState = currentMediaItemIndex
                playbackPositionState = currentPosition
                seekTo(currentItemState, playbackPositionState)
                prepare()
            }
    }

    exoPlayer.playWhenReady = true
//    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
//    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE

    DisposableEffect(
        AndroidView(factory = {
            PlayerView(context).apply {
                showController()
//                hideController()
                useController = true
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)

            }
        })
    ) {
        onDispose {
            exoPlayer.release()
            //   player?.let { exoPlayer ->
            //        playbackPosition = exoPlayer.currentPosition
            //        currentItem = exoPlayer.currentMediaItemIndex
            //        playWhenReady = exoPlayer.playWhenReady
            //        exoPlayer.release()
            //    }
            //    player = null
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpotlightScreenPreview() {
    ExoPlayerMedia3Theme {
        SpotlightScreen()
    }
}