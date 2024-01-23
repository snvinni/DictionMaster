package com.example.dictionmaster.core.data.repository

import com.example.dictionmaster.core.data.datastore.datasource.UserDataSource
import com.example.dictionmaster.core.domain.model.User
import com.example.dictionmaster.core.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override val userFlow by userDataSource::userFLow
    override val user by userDataSource::user

    override suspend fun updateLocally(block: (User) -> User): User {
        return userDataSource.update(block)
    }
}