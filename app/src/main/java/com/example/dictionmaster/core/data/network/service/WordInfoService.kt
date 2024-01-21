package com.example.dictionmaster.core.data.network.service

import com.example.dictionmaster.core.data.network.response.WordInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WordInfoService {
    @GET("entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String
    ): List<WordInfoResponse>
}