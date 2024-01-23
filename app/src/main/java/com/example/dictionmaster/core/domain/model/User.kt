package com.example.dictionmaster.core.domain.model

data class User(
    val currentSearchCount: Int,
    val wordsAlreadySearched: List<WordInfo>,
)