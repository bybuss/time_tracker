package com.example.time_tracker.data

import android.content.Context
import android.provider.Settings
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.time_tracker.data.network.GraphQLRepository
import com.example.time_tracker.data.network.TokenStoreRepository
import com.example.time_tracker.data.network.interceptors.ExtractRefreshTokenInterceptor
import com.example.time_tracker.data.network.interceptors.AddRefreshTokenInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * @author bybuss
*/

interface AppContainer {
    val tokenStoreRepository: TokenStoreRepository
    val graphQLRepository: GraphQLRepository
}

class AppContainerImpl(private val context: Context): AppContainer {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val baseUrl = "http://31.128.45.95:8000/graphql"
    override val tokenStoreRepository: TokenStoreRepository by lazy {
        TokenStoreRepository(context)
    }
    private val accessToken = runBlocking {
        tokenStoreRepository.getAccessToken().first()
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ExtractRefreshTokenInterceptor(tokenStoreRepository, coroutineScope))
        .addInterceptor(AddRefreshTokenInterceptor(tokenStoreRepository))
        .addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", accessToken)
                .addHeader("fingerprint", getDeviceFingerprint(context))
                .build()
            chain.proceed(request)
        })
        .build()

    private val apolloClient = ApolloClient.Builder()
        .serverUrl(baseUrl)
        .okHttpClient(okHttpClient)
        .build()

    override val graphQLRepository: GraphQLRepository by lazy {
        GraphQLRepository(apolloClient, tokenStoreRepository)
    }

    private fun getDeviceFingerprint(context: Context): String {
        val fingerprint = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return fingerprint
    }
}