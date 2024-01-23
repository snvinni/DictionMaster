package com.example.dictionmaster.core.domain.usecase

import com.example.dictionmaster.core.domain.model.WordInfo
import com.example.dictionmaster.core.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    private val user by userRepository::user

    suspend operator fun invoke(
        wordInfo: WordInfo
    ) {
        val wordIsStored = user.wordsAlreadySearched.find { it.id == wordInfo.id } != null

        if (wordIsStored) return

        userRepository.updateLocally {
            it.copy(
                currentSearchCount = it.currentSearchCount + 1,
                wordsAlreadySearched = it.wordsAlreadySearched + wordInfo,
            )
        }
    }
}