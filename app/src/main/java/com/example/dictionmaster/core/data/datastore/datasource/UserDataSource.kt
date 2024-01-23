package com.example.dictionmaster.core.data.datastore.datasource

import androidx.datastore.core.DataStore
import com.example.dictionmaster.core.data.datastore.mapper.apply
import com.example.dictionmaster.core.data.datastore.mapper.toModel
import com.example.dictionmaster.core.data.datastore.proto.user.DataUser
import com.example.dictionmaster.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

interface UserDataSource {
    val userFLow: Flow<User>
    val user: User

    suspend fun setUser(user: User): User

    suspend fun update(block: (User) -> User): User
}

internal class UserDatasourceImpl(
    private val dataStore: DataStore<DataUser>,
) : UserDataSource {

    override val userFLow = dataStore.data.map { dataUser ->
        dataUser.toModel()
    }

    override val user: User
        get() = runBlocking {
            userFLow.first()
        }

    override suspend fun setUser(user: User): User {
        return dataStore.updateData {
            it.toBuilder()
                .apply(user)
                .build()
        }.toModel()
    }

    override suspend fun update(
        block: (User) -> User
    ): User = setUser(
        block(
            user,
        ),
    )
}