package com.example.dictionmaster.core.data.datastore.mapper

import com.example.dictionmaster.core.data.datastore.proto.user.DataUser
import com.example.dictionmaster.core.data.datastore.proto.wordinfo.DataWordInfo
import com.example.dictionmaster.core.domain.model.User
import com.example.dictionmaster.core.domain.model.WordInfo

fun DataUser.toModel() = User(
    currentSearchCount = currentSearchCount,
    wordsAlreadySearched = wordsAlreadySearchedList.map {
        WordInfo(
            id = it.id,
            word = it.word,
            phonetics = it.phoneticsList.map { phonetic ->
                WordInfo.Phonetic(
                    text = phonetic.text,
                    audio = phonetic.audio,
                )
            },
            meanings = it.meaningsList.map { meaning ->
                WordInfo.Meaning(
                    partOfSpeech = meaning.partOfSpeech,
                    definitions = meaning.definitionsList.map { definition ->
                        WordInfo.Meaning.Definition(
                            definition = definition.definition,
                            example = definition.example,
                        )
                    }
                )
            }
        )
    },
)

fun DataUser.Builder.apply(user: User) = apply {
    currentSearchCount = user.currentSearchCount
    apply(user.wordsAlreadySearched)
}


@JvmName("applyWordsAlreadySearched")
fun DataUser.Builder.apply(
    wordsAlreadySearched: List<WordInfo>
) = apply {
    clearWordsAlreadySearched()
    addAllWordsAlreadySearched(
        wordsAlreadySearched.map { word ->
            DataWordInfo.newBuilder()
                .setId(word.id)
                .setWord(word.word)
                .apply(word.phonetics)
                .apply(word.meanings)
                .build()
        }
    )
}

@JvmName("applyPhonetics")
fun DataWordInfo.Builder.apply(
    phonetics: List<WordInfo.Phonetic>
) = apply {
    clearPhonetics()
    addAllPhonetics(
        phonetics.map { phonetic ->
            DataWordInfo.Phonetics.newBuilder()
                .setText(phonetic.text)
                .setAudio(phonetic.audio)
                .build()
        }
    )
}

@JvmName("applyMeanings")
fun DataWordInfo.Builder.apply(
    meanings: List<WordInfo.Meaning>
) = apply {
    clearMeanings()
    addAllMeanings(
        meanings.map { meaning ->
            DataWordInfo.Meaning.newBuilder()
                .setPartOfSpeech(meaning.partOfSpeech)
                .apply(meaning.definitions)
                .build()
        }
    )
}

@JvmName("applyDefinitions")
fun DataWordInfo.Meaning.Builder.apply(
    definitions: List<WordInfo.Meaning.Definition>
) = apply {
    clearDefinitions()
    addAllDefinitions(
        definitions.map { definition ->
            DataWordInfo.Meaning.Definition.newBuilder()
                .setDefinition(definition.definition)
                .setExample(definition.example)
                .build()
        }
    )
}