package com.example.dictionmaster.core.data.network.mapper

import com.example.dictionmaster.core.data.network.response.WordResponse
import com.example.dictionmaster.core.domain.model.Word

fun WordResponse.toModel() = Word(
    id = -1,
    word = word,
    phonetics = phonetics.map { phonetic ->
        Word.Phonetic(
            text = phonetic.text ?: "",
            audio = phonetic.audio
        )
    },
    meanings = meanings.map { meaning ->
        Word.Meaning(
            partOfSpeech = meaning.partOfSpeech,
            definitions = meaning.definitions.map { definition ->
                Word.Meaning.Definition(
                    definition = definition.definition,
                    example = definition.example ?: "",
                )
            }
        )
    }
)