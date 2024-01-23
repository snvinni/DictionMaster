package com.example.dictionmaster.feature.wordinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionmaster.core.domain.model.Word
import com.example.dictionmaster.core.domain.repository.UserRepository
import com.example.dictionmaster.core.domain.usecase.IsUserHasReachedFreeSearchLimitUseCase
import com.example.dictionmaster.feature.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val isUserHasReachedFreeSearchLimitUseCase: IsUserHasReachedFreeSearchLimitUseCase,
) : ViewModel() {
    private val eventChannel = Channel<NavigationRoute>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun getCurrentWord(id: Int): Word {
        return userRepository.user.wordsAlreadySearched.find { it.id == id }
            ?: throw Exception("Word not found")
    }

    fun onAction(action: Action) = viewModelScope.launch {
        when (action) {
            is Action.OnNewSearch -> {
                if (isUserHasReachedFreeSearchLimitUseCase()) {
                    eventChannel.send(NavigationRoute.NavigateToSubscribe)
                } else {
                    eventChannel.send(NavigationRoute.NavigateToSearch)
                }
            }
        }
    }
}

sealed interface Action {
    data class OnNewSearch(val hasReachedDailyLimit: Boolean) : Action

}