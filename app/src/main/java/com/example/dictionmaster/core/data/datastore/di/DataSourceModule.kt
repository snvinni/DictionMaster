package com.example.dictionmaster.core.data.datastore.di

import android.content.Context
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.dictionmaster.core.data.datastore.datasource.UserDataSource
import com.example.dictionmaster.core.data.datastore.datasource.UserDatasourceImpl
import com.example.dictionmaster.core.data.datastore.serializer.UserSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

internal object DataSourceModule {

    @Module
    @InstallIn(SingletonComponent::class)
    internal object Provide {
        @Provides
        @Singleton
        fun providesUserDataSource(
            @ApplicationContext context: Context,
        ): UserDataSource = UserDatasourceImpl(
            DataStoreFactory.create(
                serializer = UserSerializer,
            ) {
                context.dataStoreFile(fileName = "user.pb")
            },
        )
    }
}