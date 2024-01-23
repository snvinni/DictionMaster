package com.example.dictionmaster.core.data.network.di

import com.example.dictionmaster.core.data.network.service.WordService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): WordService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WordService::class.java)
    }

    private const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/"
}