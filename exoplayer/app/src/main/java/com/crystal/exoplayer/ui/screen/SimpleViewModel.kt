package com.crystal.exoplayer.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crystal.exoplayer.R
import com.crystal.exoplayer.network.EpisodeData
import com.crystal.exoplayer.ui.PlayerUiState
import kotlinx.coroutines.launch
import java.io.IOException

class SimpleViewModel: ViewModel() {
    var uiState: SimplePlayerUiState by mutableStateOf(SimplePlayerUiState.Loading)
        private set


    private val realEnglishUrl: String =
//            "https://v1.wisdomhouse.co.kr/mp3/realenglish/realenglish001.mp3"
            "https://v1.wisdomhouse.co.kr/mp3/realenglish/realenglish"

    private var currentIndex = 1

    val currentEpisode : String
        get() = realEnglishUrl.plus(currentIndex.toString().padStart(3, '0')).plus(".mp3")
//        get() = realEnglishUrl.plus(String.format("%03d", currentIndex)).plus(".mp3")

    fun moveNext() = currentIndex++

    fun getInfos() {
        viewModelScope.launch {
            uiState = SimplePlayerUiState.Loading
            uiState = try {
//                PlayerUiState.Success(ProgressRepository.getInfos())
                SimplePlayerUiState.Success(currentEpisode)
            } catch (e: IOException) {
                SimplePlayerUiState.Error
            }
        }

    }


}


sealed interface SimplePlayerUiState {
    data class Success(val url: String) : SimplePlayerUiState
    object Loading : SimplePlayerUiState
    object Error : SimplePlayerUiState
}
