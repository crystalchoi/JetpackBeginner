package com.example.amphibians.ui.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibiansRepository
import com.example.amphibians.network.AmphibiansApi
import com.example.amphibians.network.AnimalData
import kotlinx.coroutines.launch
import java.io.IOException

class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {
    var uiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getInfos()
    }

    fun getInfos() {
        //marsUiState = "Set the Mars API status response here!"
        viewModelScope.launch {
            uiState = AmphibiansUiState.Loading
            uiState = try {
                AmphibiansUiState.Success(amphibiansRepository.getInfos())
            } catch (e: IOException) {
                AmphibiansUiState.Error
            }
        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }

}



sealed interface AmphibiansUiState {
    data class Success(val infos: List<AnimalData>) : AmphibiansUiState
    object Loading : AmphibiansUiState
    object Error : AmphibiansUiState
}
