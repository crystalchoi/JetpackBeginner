package com.crystal.exoplayer.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.crystal.exoplayer.PlayerApplication
import com.crystal.exoplayer.data.ProgressRepository
import com.crystal.exoplayer.network.EpisodeData
import kotlinx.coroutines.launch
import java.io.IOException


private val TAG = "PlayerViewModel"
class PlayerViewModel(private val progressRepository: ProgressRepository): ViewModel() {
    var uiState: PlayerUiState by mutableStateOf(PlayerUiState.Loading)
        private set

    init {
//        Log.d(TAG, "ViewModel instance created")
        getInfos()
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }

    fun getInfos() {
        //marsUiState = "Set the Mars API status response here!"
        viewModelScope.launch {
            uiState = PlayerUiState.Loading
            uiState = try {
//                PlayerUiState.Success(ProgressRepository.getInfos())
                val list = listOf(EpisodeData(name = "name", type= "type", description = "desc"), )
                PlayerUiState.Success(list)
            } catch (e: IOException) {
                PlayerUiState.Error
            }
        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PlayerApplication)
                val progressRepository = application.container.progressRepository
                PlayerViewModel(progressRepository = progressRepository)
            }
        }
    }
}



sealed interface PlayerUiState {
    data class Success(val infos: List<EpisodeData>) : PlayerUiState
    object Loading : PlayerUiState
    object Error : PlayerUiState
}
