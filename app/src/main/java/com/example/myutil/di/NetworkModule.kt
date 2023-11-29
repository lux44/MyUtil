package com.example.myutil.di

import com.example.myutil.BuildConfig
import com.example.myutil.repositories.DataStoreRepository
import com.example.myutil.utils.ConstVariables.BASE_API_URL
import com.example.myutil.utils.ConstVariables.DEV_BASE_API_URL
import com.example.myutil.utils.ModeChanger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Qualifier
import javax.inject.Singleton
import android.os.Build
import com.example.myutil.utils.GzipInterceptor
import com.example.myutil.utils.TokenRefreshInterceptor
import okhttp3.Protocol

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiServer

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthServer

    @ApiServer
    @Provides
    fun provideBaseApiUrl(dataStoreRepository: DataStoreRepository) = if (ModeChanger.getInstance(dataStoreRepository).getMode() == ModeChanger.MODE_DEBUG && com.example.myutil.BuildConfig.DEBUG) {
        DEV_BASE_API_URL
        Build.VERSION.RELEASE
    } else {
        BASE_API_URL
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        dataStoreRepository: DataStoreRepository
    ): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(TokenRefreshInterceptor(dataStoreRepository))
            .addInterceptor(provideGzipInterceptor())
            .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGzipInterceptor(): GzipInterceptor {
        return GzipInterceptor()
    }
}