package com.example.dictionmaster.core.data.network.mapper

import com.example.dictionmaster.core.data.network.response.WordInfoResponse
import com.example.dictionmaster.core.domain.model.WordInfo

fun WordInfoResponse.toModel() = WordInfo(
    id = -1,
    word = word,
    phonetics = phonetics.map { phonetic ->
        WordInfo.Phonetic(
            text = phonetic.text,
            audio = phonetic.audio
        )
    },
    meanings = meanings.map { meaning ->
        WordInfo.Meaning(
            partOfSpeech = meaning.partOfSpeech,
            definitions = meaning.definitions.map { definition ->
                WordInfo.Meaning.Definition(
                    definition = definition.definition,
                    example = definition.example ?: "",
                )
            }
        )
    }
)