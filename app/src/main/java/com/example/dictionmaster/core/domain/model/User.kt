package com.example.dictionmaster.core.domain.model

data class User(
    val currentSearchCount: Int,
    val firstRequestTimeInMillis: Long,
    val wordsAlreadySearched: List<Word>,
)