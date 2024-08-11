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
    private val token = "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJ0eXBlIjoiYWNjZXNzIiwic3ViIjoiZDY5NWYyNTYtMjFmMy00YmEwLTljNTItYmVjNzYyOTgxM2EyIiwiZW1haWwiOiJiYWJha2FwYTcyOUBnbWFpbC5jb20iLCJyb2xlX2lkIjoxLCJleHAiOjE3MjM0MTUxMTAsImlhdCI6MTcyMzMyNTExMH0.W3gD6eHj9iQ1zF_koLY_2OCZ3Mi7Ge_1zZeZ84JnPN-74u1yS-yfBoAQ6CXCOa7BozvGjdh30b1JlFyXLXDC1POguso_tlRGeVyJbhCQEgY3BJqIgk8l1BpQCvzVmfGZJcygGRB7nRRL1HMMlrrzFkK3wHR5-JmNMgHN_-Q7frcBbYJyaMzYchiVVFcgDvlhZXgfzTOrNHhGuw_M2OFqwbfrVEA3VS8ZMUdFrsQThdqPYpI0cEja1Q04q8t9DD0rx0HaimczMxYqRGHN1JNs-wPvDlgkFERIaWNOpRv74zbc2IeiOI8I_SHIru1pxkMAFZ5zBj7g_u_1MSH9pzjoGg"

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
