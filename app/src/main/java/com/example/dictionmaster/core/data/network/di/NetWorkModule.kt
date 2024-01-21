package com.example.dictionmaster.core.data.network.di

import com.example.dictionmaster.core.data.network.service.WordInfoService
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
    fun provideRetrofit(): WordInfoService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WordInfoService::class.java)
    }

    private const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/"
}