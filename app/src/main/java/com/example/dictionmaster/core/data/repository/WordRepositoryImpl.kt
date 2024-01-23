package com.example.dictionmaster.core.data.repository

import com.example.dictionmaster.core.data.network.mapper.toModel
import com.example.dictionmaster.core.data.network.service.WordService
import com.example.dictionmaster.core.domain.model.Word
import com.example.dictionmaster.core.domain.repository.WordRepository
import com.example.dictionmaster.core.domain.util.Resource
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val service: WordService
) : WordRepository {
    override suspend fun getWordInfo(word: String): Resource.Result<Word, Throwable> {
        return runCatching {
            val responseData = service.getWord(word)

            Resource.Result.Success(responseData.first().toModel())
        }.getOrElse {
            Resource.Result.Error(it)
        }
    }
}