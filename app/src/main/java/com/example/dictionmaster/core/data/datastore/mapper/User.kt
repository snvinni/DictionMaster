package com.example.dictionmaster.core.data.datastore.mapper

import com.example.dictionmaster.core.data.datastore.proto.user.DataUser
import com.example.dictionmaster.core.data.datastore.proto.wordinfo.DataWordInfo
import com.example.dictionmaster.core.domain.model.User
import com.example.dictionmaster.core.domain.model.Word

fun DataUser.toModel() = User(
    currentSearchCount = currentSearchCount,
    firstRequestTimeInMillis = firstRequestTimeInMillis,
    wordsAlreadySearched = wordsAlreadySearchedList.map {
        Word(
            id = it.id,
            word = it.word,
            phonetics = it.phoneticsList.map { phonetic ->
                Word.Phonetic(
                    text = phonetic.text,
                    audio = phonetic.audio,
                )
            },
            meanings = it.meaningsList.map { meaning ->
                Word.Meaning(
                    partOfSpeech = meaning.partOfSpeech,
                    definitions = meaning.definitionsList.map { definition ->
                        Word.Meaning.Definition(
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
    firstRequestTimeInMillis = user.firstRequestTimeInMillis
    apply(user.wordsAlreadySearched)
}


@JvmName("applyWordsAlreadySearched")
fun DataUser.Builder.apply(
    wordsAlreadySearched: List<Word>
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
    phonetics: List<Word.Phonetic>
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
    meanings: List<Word.Meaning>
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
    definitions: List<Word.Meaning.Definition>
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