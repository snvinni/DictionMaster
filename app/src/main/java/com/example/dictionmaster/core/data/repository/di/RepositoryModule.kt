package com.example.dictionmaster.core.data.repository.di

import com.example.dictionmaster.core.data.repository.UserRepositoryImpl
import com.example.dictionmaster.core.data.repository.WordRepositoryImpl
import com.example.dictionmaster.core.domain.repository.UserRepository
import com.example.dictionmaster.core.domain.repository.WordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsWordInfoRepository(
        wordInfoRepositoryImpl: WordRepositoryImpl,
    ): WordRepository

    @Binds
    @Singleton
    abstract fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository
}