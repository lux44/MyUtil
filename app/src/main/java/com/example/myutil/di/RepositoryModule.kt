package com.example.myutil.di

import android.content.Context
import com.example.myutil.repositories.DataStoreRepository
import com.example.myutil.repositories.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext app: Context): DataStoreRepository = DataStoreRepositoryImpl(app)
}