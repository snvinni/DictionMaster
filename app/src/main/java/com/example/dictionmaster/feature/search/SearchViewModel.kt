package com.example.dictionmaster.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionmaster.core.domain.usecase.GetWordInfoUseCase
import com.example.dictionmaster.core.domain.usecase.Result
import com.example.dictionmaster.core.domain.usecase.UpdateUserUseCase
import com.example.dictionmaster.feature.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getWordInfoUseCase: GetWordInfoUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) : ViewModel() {

    val word = savedStateHandle.getStateFlow("word", "")

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvents = Channel<NavigationRoute>(Channel.BUFFERED)
    val navigationEvents = _navigationEvents.receiveAsFlow()

    fun onAction(action: Action) = viewModelScope.launch {
        when (action) {
            is Action.OnSearch -> onSearch()
            is Action.OnWordChange -> onWordChange(action.word)
        }
    }

    private fun onSearch() = viewModelScope.launch {
        _uiState.update {
            UiState.Loading
        }

        when (val response = getWordInfoUseCase(word.value)) {
            is Result.Error -> {
                if (response.isUserHasReachedFreeSearchLimit) {
                    _navigationEvents.send(
                        NavigationRoute.NavigateToSubscribe
                    )
                } else {
                    _uiState.update {
                        UiState.Error(
                            message = GENERIC_ERROR_MESSAGE,
                        )
                    }
                }
            }

            is Result.Success -> {
                updateUserUseCase(response.data)

                _navigationEvents.send(
                    NavigationRoute.NavigateToWordInfo(response.data.id)
                )
            }
        }
    }

    private fun onWordChange(word: String) {
        savedStateHandle["word"] = word
    }

    companion object {
        const val GENERIC_ERROR_MESSAGE = "Something went wrong, try again!"
    }
}

sealed interface UiState {
    data object Loading : UiState

    data class Error(
        val message: String
    ) : UiState

    data object Idle : UiState
}

sealed interface Action {
    data object OnSearch : Action

    data class OnWordChange(val word: String) : Action
}