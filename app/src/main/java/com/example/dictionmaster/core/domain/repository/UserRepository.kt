package com.example.dictionmaster.core.domain.repository

import com.example.dictionmaster.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val userFlow: Flow<User>
    val user: User

    suspend fun updateLocally(block: (User) -> User): User
}