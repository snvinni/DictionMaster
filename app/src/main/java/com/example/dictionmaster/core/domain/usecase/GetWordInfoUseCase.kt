package com.example.dictionmaster.core.domain.usecase

import com.example.dictionmaster.core.domain.model.WordInfo
import com.example.dictionmaster.core.domain.repository.WordInfoRepository
import com.example.dictionmaster.core.domain.util.Resource
import javax.inject.Inject

class GetWordInfoUseCase @Inject constructor(
    private val repository: WordInfoRepository
) {
    suspend operator fun invoke(word: String): Resource.Result<WordInfo, Throwable> {
        return when (val response = repository.getWordInfo(word)) {
            is Resource.Result.Error -> response

            is Resource.Result.Success -> {
                val data = response.data

                Resource.Result.Success(data)
            }
        }
    }
}
