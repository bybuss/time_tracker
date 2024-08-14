package com.example.time_tracker.data.network

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.time_tracker.data.network.api.TimeTrackerRepository
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
    private val token = "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJ0eXBlIjoiYWNjZXNzIiwic3ViIjoiZDY5NWYyNTYtMjFmMy00YmEwLTljNTItYmVjNzYyOTgxM2EyIiwiZW1haWwiOiJiYWJha2FwYTcyOUBnbWFpbC5jb20iLCJyb2xlX2lkIjoxLCJleHAiOjE3MjM3Mzk5NjAsImlhdCI6MTcyMzY0OTk2MH0.Q_WDAHZ3hqO5X36OXfGHmyv7olyU7oPtA1szOEEeKE3eL-QdB1N5J3Ygy_BLneTCF0_doNDCZXp3N31CoZgYCwx55PGaSmIkmVkZb0ymSPsBNFkb2fpgeU44wXEguQ18hYu-JUWVOwqW0FiuFEXUI5RRIQReIYLRd5Z6VF_4GNVcsWGMq7qjcqEY4t7zvsKoWV-VNt-V-ta0CDjhf49ySfsAXsfVl-4yDW2WMtHFjkpKtRQjcgHOxj3Te_Hp8qQIFYHESIFNcVm1PqDH35HT1ZOazuwuqaKpmPL0qRukYlMDqHvrdW8rgzh_HhwZAjSA8DhKztQUVHlfbHhG_bkoPg"
//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(AuthorizationInterceptor())
//        .build()
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", token)
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
        TimeTrackerRepository(apolloClient)
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
