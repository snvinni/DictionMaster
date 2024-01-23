package com.example.dictionmaster.core.domain.repository

import com.example.dictionmaster.core.domain.model.Word
import com.example.dictionmaster.core.domain.util.Resource

interface WordRepository {
    suspend fun getWordInfo(
        word: String
    ): Resource.Result<Word, Throwable>
}