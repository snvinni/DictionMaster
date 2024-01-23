package com.example.dictionmaster.core.domain.usecase

import com.example.dictionmaster.core.domain.model.Word
import com.example.dictionmaster.core.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    private val user by userRepository::user

    suspend operator fun invoke(
        word: Word
    ) {
        val wordIsStored = user.wordsAlreadySearched.find { it.id == word.id } != null

        if (wordIsStored) return

        userRepository.updateLocally {
            it.copy(
                currentSearchCount = it.currentSearchCount + 1,
                wordsAlreadySearched = it.wordsAlreadySearched + word,
            )
        }
    }
}