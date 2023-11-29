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
import com.example.myutil.data.remote.api.AuthService
import com.example.myutil.utils.ConstVariables.BASE_URL
import com.example.myutil.utils.ConstVariables.DEV_BASE_URL
import com.example.myutil.utils.GzipInterceptor
import com.example.myutil.utils.TokenRefreshInterceptor
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    /**
     * Qualifier - 같은 리턴 타입이 있을 경우
     * URL 구별용
     * */
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiServer

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthServer

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiOkHttpClient



    /**
     * Provides
     * URL 주입
     * */

    @ApiServer
    @Provides
    fun provideBaseApiUrl(dataStoreRepository: DataStoreRepository) = if (ModeChanger.getInstance(dataStoreRepository).getMode() == ModeChanger.MODE_DEBUG && com.example.myutil.BuildConfig.DEBUG) {
        DEV_BASE_API_URL
    } else {
        BASE_API_URL
    }

    @AuthServer
    @Provides
    fun provideBaseUrl(dataStoreRepository: DataStoreRepository) = if (ModeChanger.getInstance(dataStoreRepository).getMode() == ModeChanger.MODE_DEBUG && BuildConfig.DEBUG) {
        DEV_BASE_URL
    } else {
        BASE_URL
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

    @ApiOkHttpClient
    @Singleton
    @Provides
    fun provideApiOkHttpClient(
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


    /**
     * URL 에 따른 Retrofit Builder 객체 생성
     * */
    @ApiServer
    @Singleton
    @Provides
    fun provideRetrofit(@ApiOkHttpClient client: OkHttpClient, @ApiServer baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @AuthServer
    @Singleton
    @Provides
    fun provideAuthRetrofit(client: OkHttpClient, @AuthServer baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Service 와 Retrofit 객체 연결
     * */
    @AuthServer
    @Singleton
    @Provides
    fun provideAuthService(@AuthServer retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}