package com.example.dictionmaster.core.data.repository.di

import com.example.dictionmaster.core.data.repository.WordInfoRepositoryImpl
import com.example.dictionmaster.core.domain.repository.WordInfoRepository
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
        wordInfoRepositoryImpl: WordInfoRepositoryImpl,
    ): WordInfoRepository
}