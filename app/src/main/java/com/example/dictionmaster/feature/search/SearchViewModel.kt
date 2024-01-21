package com.example.dictionmaster.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionmaster.core.domain.model.WordInfo
import com.example.dictionmaster.core.domain.usecase.GetWordInfoUseCase
import com.example.dictionmaster.core.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getWordInfoUseCase: GetWordInfoUseCase
) : ViewModel() {

    val word = savedStateHandle.getStateFlow("word", "")

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun onAction(action: Action) {
        when (action) {
            is Action.OnSearch -> onSearch()
            is Action.OnWordChange -> onWordChange(action.word)
        }
    }

    private fun onSearch() = viewModelScope.launch {
        _uiState.update {
            UiState.Loading
        }

        val response = getWordInfoUseCase(word.value)

        _uiState.update {
            when (response) {
                is Resource.Result.Error -> UiState.Error(GENERIC_ERROR_MESSAGE)
                is Resource.Result.Success -> UiState.Success(response.data)
            }
        }
    }

    private fun onWordChange(word: String) {
        savedStateHandle["word"] = word
    }

    companion object {
        const val GENERIC_ERROR_MESSAGE = "Something went wrong"
    }
}

sealed interface UiState {
    data class Success(val wordInfo: WordInfo) : UiState

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