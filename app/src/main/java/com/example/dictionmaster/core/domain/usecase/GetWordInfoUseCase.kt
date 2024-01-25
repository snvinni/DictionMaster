package com.example.dictionmaster.core.domain.usecase

import com.example.dictionmaster.Constants
import com.example.dictionmaster.core.domain.model.Word
import com.example.dictionmaster.core.domain.repository.UserRepository
import com.example.dictionmaster.core.domain.repository.WordRepository
import com.example.dictionmaster.core.domain.util.Resource
import javax.inject.Inject

class GetWordInfoUseCase @Inject constructor(
    private val repository: WordRepository,
    userRepository: UserRepository
) {
    val user = userRepository.user

    suspend operator fun invoke(word: String): Result {
        val wordInfoAlreadySearched = user.wordsAlreadySearched.find { it.word == word }

        if (wordInfoAlreadySearched != null) {
            return Result.Success(
                data = wordInfoAlreadySearched,
            )
        }

        if (user.currentSearchCount > Constants.DAILY_LIMIT_OF_SEARCHES) {
            return Result.Error(
                isUserHasReachedFreeSearchLimit = true,
            )
        }

        return when (val response = repository.getWordInfo(word)) {
            is Resource.Result.Error -> {
                Result.Error(
                    isUserHasReachedFreeSearchLimit = false,
                )
            }

            is Resource.Result.Success -> {
                val wordId = if (user.wordsAlreadySearched.isEmpty()) 1 else
                    user.wordsAlreadySearched.last().id + 1

                val wordInfo = response.data.copy(
                    id = wordId,
                )

                Result.Success(
                    data = wordInfo,
                )
            }
        }
    }
}

sealed interface Result {
    data class Success(val data: Word) : Result
    data class Error(val isUserHasReachedFreeSearchLimit: Boolean) : Result
}
