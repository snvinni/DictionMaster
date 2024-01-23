package com.example.dictionmaster.core.data.network.service

import com.example.dictionmaster.core.data.network.response.WordResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WordService {
    @GET("entries/en/{word}")
    suspend fun getWord(
        @Path("word") word: String
    ): List<WordResponse>
}