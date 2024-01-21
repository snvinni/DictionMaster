package com.example.dictionmaster.core.domain.util

sealed interface Resource<out T, out E> {

    sealed interface Result<out T, out E> : Resource<T, E> {

        data class Success<T>(val data: T) : Result<T, Nothing>

        data class Error<E>(val error: E) : Result<Nothing, E>
    }

    data object Loading : Resource<Nothing, Nothing>
}
