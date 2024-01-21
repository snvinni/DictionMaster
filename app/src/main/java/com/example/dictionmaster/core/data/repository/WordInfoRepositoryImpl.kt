package com.example.dictionmaster.core.data.repository

import com.example.dictionmaster.core.data.network.mapper.toModel
import com.example.dictionmaster.core.data.network.service.WordInfoService
import com.example.dictionmaster.core.domain.model.WordInfo
import com.example.dictionmaster.core.domain.repository.WordInfoRepository
import com.example.dictionmaster.core.domain.util.Resource
import javax.inject.Inject

class WordInfoRepositoryImpl @Inject constructor(
    private val service: WordInfoService
) : WordInfoRepository {
    override suspend fun getWordInfo(word: String): Resource.Result<WordInfo, Exception> {
        return runCatching {
            val responseData = service.getWordInfo(word)

            Resource.Result.Success(responseData.toModel())
        }.getOrElse {
            Resource.Result.Error(Exception())
        }
    }
}