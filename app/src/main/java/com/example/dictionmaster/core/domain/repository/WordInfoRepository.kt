package com.example.dictionmaster.core.domain.repository

import com.example.dictionmaster.core.domain.model.WordInfo
import com.example.dictionmaster.core.domain.util.Resource

interface WordInfoRepository {
    suspend fun getWordInfo(
        word: String
    ): Resource.Result<WordInfo, Throwable>
}