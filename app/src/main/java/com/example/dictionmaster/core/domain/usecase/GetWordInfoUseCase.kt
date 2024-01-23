package com.example.dictionmaster.core.domain.usecase

import com.example.dictionmaster.core.domain.model.WordInfo
import com.example.dictionmaster.core.domain.repository.UserRepository
import com.example.dictionmaster.core.domain.repository.WordInfoRepository
import com.example.dictionmaster.core.domain.util.Resource
import javax.inject.Inject

class GetWordInfoUseCase @Inject constructor(
    private val repository: WordInfoRepository,
    userRepository: UserRepository
) {
    val user = userRepository.user

    suspend operator fun invoke(word: String): Resource.Result<WordInfo, Throwable> {
        val wordInfoAlreadySearched = user.wordsAlreadySearched.find { it.word == word }

        if (wordInfoAlreadySearched != null) {
            return Resource.Result.Success(
                data = wordInfoAlreadySearched,
            )
        }

        return when (val response = repository.getWordInfo(word)) {
            is Resource.Result.Error -> response

            is Resource.Result.Success -> {
                val wordId = if (user.wordsAlreadySearched.isEmpty()) 1 else
                    user.wordsAlreadySearched.last().id + 1

                val wordInfo = response.data.copy(
                    id = wordId,
                )

                Resource.Result.Success(
                    data = wordInfo,
                )
            }
        }
    }
}

