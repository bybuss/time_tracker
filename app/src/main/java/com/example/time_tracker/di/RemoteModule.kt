package com.example.time_tracker.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.time_tracker.BuildConfig
import com.example.time_tracker.data.local.dataStore.TokenDataSource
import com.example.time_tracker.data.network.interceptors.AddRefreshTokenInterceptor
import com.example.time_tracker.data.network.interceptors.ExtractRefreshTokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * @author bybuss
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = BuildConfig.BASE_API_URL

    @Provides
    @Singleton
    fun provideAccessToken(tokenDataSource: TokenDataSource): String {
        return runBlocking {
            tokenDataSource.getAccessToken().first()
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenDataSource: TokenDataSource,
        coroutineScope: CoroutineScope,
        accessToken: String,
        deviceFingerprint: String
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ExtractRefreshTokenInterceptor(tokenDataSource, coroutineScope))
            .addInterceptor(AddRefreshTokenInterceptor(tokenDataSource))
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", accessToken)
                    .addHeader("fingerprint", deviceFingerprint)
                    .build()
                chain.proceed(request)
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideApolloClient(baseUrl: String, okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(baseUrl)
            .okHttpClient(okHttpClient)
            .build()
    }
}