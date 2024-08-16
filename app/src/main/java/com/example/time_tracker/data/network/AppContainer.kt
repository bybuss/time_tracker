package com.example.time_tracker.data.network

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.time_tracker.data.network.interceptors.ExtractRefreshTokenInterceptor
import com.example.time_tracker.data.network.interceptors.AddRefreshTokenInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * @author bybuss
*/

interface AppContainer {
    val timeTrackerRepository: TimeTrackerRepository
}

class AppContainerImpl(private val context: Context): AppContainer {
    private val baseUrl = "http://31.128.45.95:8000/graphql"
    private var tokenManager = TokenManager() // TODO: ЗАМЕНИТЬ ПОТОМ tokenManager НА БД

//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(AuthorizationInterceptor())
//        .build()
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ExtractRefreshTokenInterceptor(tokenManager))
        .addInterceptor(AddRefreshTokenInterceptor(tokenManager))
        .addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", tokenManager.getAccessToken())
                .addHeader("fingerprint", getDeviceFingerprint(context))
                .build()
            chain.proceed(request)
        })
        .build()

    private val apolloClient = ApolloClient.Builder()
        .serverUrl(baseUrl)
        .okHttpClient(okHttpClient)
        .build()

    override val timeTrackerRepository: TimeTrackerRepository by lazy {
        TimeTrackerRepository(apolloClient, tokenManager) // TODO: ЗАМЕНИТЬ ПОТОМ tokenManager НА БД
    }

    private fun getDeviceFingerprint(context: Context): String {
        val fingerprint = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        Log.d("Device fingerprint", fingerprint)
        return fingerprint
    }
}

//private class AuthorizationInterceptor() : Interceptor {
//    val context: Context = applicationContext
//
//    private val token = "TOKEN"
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request().newBuilder()
//            .addHeader("Authorization", token)
//            .addHeader("fingerprint", getDeviceFingerprint(context))
//            .build()
//
//        return chain.proceed(request)
//    }
//}
