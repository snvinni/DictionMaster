package com.example.dictionmaster.feature.wordinfo

import androidx.lifecycle.ViewModel
import com.example.dictionmaster.core.domain.model.WordInfo
import com.example.dictionmaster.core.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    fun getCurrentWord(id: Int): WordInfo {
        return userRepository.user.wordsAlreadySearched.find { it.id == id }
            ?: throw Exception("Word not found")
    }
}