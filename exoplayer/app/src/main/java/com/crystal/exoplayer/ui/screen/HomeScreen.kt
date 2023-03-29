package com.crystal.exoplayer.ui

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.crystal.exoplayer.R.*
import com.crystal.exoplayer.network.EpisodeData
import com.crystal.exoplayer.ui.screen.OldMediaPlayer
import com.crystal.exoplayer.ui.screen.SimpleViewModel
import com.crystal.exoplayer.ui.theme.ExoplayerTheme

private val TAG = "PlayerHomeScreen"

@Composable
fun HomeScreen(uiState: SimpleViewModel
               , retryAction: () -> Unit
               ,  modifier: Modifier = Modifier
) {
//    when (uiState) {
//        is PlayerUiState.Loading -> LoadingScreen(modifier)
//        is PlayerUiState.Error -> ErrorScreen(retryAction, modifier)
//        is PlayerUiState.Success -> PlayerListScreen(uiState.infos, modifier)

    PlayerListScreen(uiState, modifier)
}

@Composable
fun PlayerListScreen(viewModel: SimpleViewModel, modifier: Modifier = Modifier) {
//fun PlayerListScreen(episodeDatas: List<EpisodeData>, modifier: Modifier = Modifier) {

// val url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
//    Log.i(TAG, url)
//    VideoView(videoUri = url)

    Log.i(TAG, viewModel.currentEpisode)
//    VideoView(viewModel.currentEpisode)
    OldMediaPlayer(viewModel.currentEpisode)

//    LazyColumn(
////        columns =  GridCells.Adaptive(minSize = 250.dp)
//        modifier = modifier.fillMaxWidth()
//        , verticalArrangement = Arrangement.spacedBy(16.dp)
//        , contentPadding = PaddingValues(4.dp)
//    ) {
//        items(items = episodeDatas, key = { data -> data.name }) { data ->
////            AnimalCard(data)
//        }
//    }
}



@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = com.crystal.exoplayer.R.drawable.loading_img),
            contentDescription = stringResource(id = com.crystal.exoplayer.R.string.loading)
        )
    }
}


@Composable
fun ErrorScreen(retryAction: () -> kotlin.Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(com.crystal.exoplayer.R.string.loading_failed))
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = retryAction) {
            Text(stringResource(com.crystal.exoplayer.R.string.retry))
//            Text(stringResource(com.example.amphibians.R.string.retry))
        }
    }
}



@Composable
fun EpisodeCard(data : EpisodeData, modifier: Modifier = Modifier) {
}

@Preview(showBackground = true)
@Composable
fun AnimalCardPreview() {
    ExoplayerTheme {
        EpisodeCard(EpisodeData(name = "Great Basin Spadefoot"
            , type = "Toad"
            , description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots."
//            , imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
        ))
    }
}


@Composable
fun MyPlayer(player : ExoPlayer?, playerView : PlayerView) {
    val sampleVideo : String = stringResource(com.crystal.exoplayer.R.string.sample_url)
    val context = LocalContext.current
//    val player = ExoPlayer.Builder(context).build()
    var player =  if (player != null) {
        player!!
    } else {
        ExoPlayer.Builder(context).build()
    }
//    val playerView = PlayerView(context)
    val mediaItem = MediaItem.fromUri(sampleVideo)

    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }
    player.setMediaItem(mediaItem)
    playerView.player = player
    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady

    }


}


//@Composable
//fun AudioPlayView(context: Context, myUri: String) {
//    val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
//        setAudioStreamType(AudioManager.STREAM_MUSIC)
//        setDataSource(context, myUri)
//        prepare()
//        start()
//    }
//}

@Composable
fun VideoView(videoUri: String) {

    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }

    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context)
        .build()
        .also { exoPlayer ->
            val mediaItem = MediaItem.Builder()
                .setUri(videoUri)
                .build()
            exoPlayer.setMediaItem(mediaItem)
//            exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
// Set a list of media sources as initial playlist.
//            exoPlayer.setMediaSources(listOfMediaSources);
// Add a single media source.
//            exoPlayer.addMediaSource(anotherMediaSource);

// Can be combined with the media item API.
            exoPlayer.addMediaItem(/* index= */ 3, MediaItem.fromUri(videoUri));
            exoPlayer.prepare()
            exoPlayer.playWhenReady = playWhenReady
        }

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(
        AndroidView(factory = {
            PlayerView(context).apply {
                player = exoPlayer

            }
        })
    ) {
        val observer = LifecycleEventObserver { owner, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    exoPlayer.pause()
                }
                Lifecycle.Event.ON_RESUME -> {
                    exoPlayer.play()
                }
                else -> {}
            }
        }
        val lifecycle = lifecycleOwner.value.lifecycle
        lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycle.removeObserver(observer)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    var player: ExoPlayer? = null
    var playerView: PlayerView? = null

    ExoplayerTheme {
//        TextViewCompose("Android")
        playerView = PlayerView(LocalContext.current)
        MyPlayer(player, playerView!!)
        VideoView(videoUri = stringResource(com.crystal.exoplayer.R.string.sample_url))
    }
}