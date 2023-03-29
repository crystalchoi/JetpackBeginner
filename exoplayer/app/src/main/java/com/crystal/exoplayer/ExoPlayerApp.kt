package com.crystal.exoplayer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.crystal.exoplayer.ui.HomeScreen
import com.crystal.exoplayer.ui.PlayerViewModel
import com.crystal.exoplayer.ui.screen.SimpleViewModel


@Composable
fun ExoPlayerApp(viewModel: SimpleViewModel, modifier: Modifier = Modifier) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
//        playerView = PlayerView(LocalContext.current)
//        MyPlayer(player, playerView!!)
//        VideoView(videoUri = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")

//        HomeScreen(
//            uiState = viewModel.uiState,
//            retryAction = viewModel::getInfos
//        )

        HomeScreen(uiState = viewModel, retryAction = {})
    }
}