package com.example.dictionmaster.core.data.network.response

import com.google.gson.annotations.SerializedName

data class WordInfoResponse(
    @SerializedName("word")
    val word: String,
    @SerializedName("phonetic")
    val phonetic: String,
    @SerializedName("phonetics")
    val phonetics: List<Phonetics>,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("meanings")
    val meanings: List<Meanings>,
) {
    data class Phonetics(
        @SerializedName("text")
        val text: String?,
        @SerializedName("audio")
        val audio: String,
    )

    data class Meanings(
        @SerializedName("partOfSpeech")
        val partOfSpeech: String,
        @SerializedName("definitions")
        val definitions: List<Definitions>,
    ) {
        data class Definitions(
            @SerializedName("definition")
            val definition: String,
            @SerializedName("example")
            val example: String?,
        )
    }
}