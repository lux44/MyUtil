package com.example.myutil.di

import android.content.Context
import com.example.myutil.data.local.dao.MyProfileDao
import com.example.myutil.data.remote.api.UserInfoService
import com.example.myutil.repositories.DataStoreRepository
import com.example.myutil.repositories.DataStoreRepositoryImpl
import com.example.myutil.repositories.UserInfoRepository
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

    @Singleton
    @Provides
    fun provideUserInfoRepository(
        @NetworkModule.ApiServer userInfoService: UserInfoService,
        myProfileDao: MyProfileDao
    ): UserInfoRepository =
        UserInfoRepository(userInfoService, myProfileDao)
}