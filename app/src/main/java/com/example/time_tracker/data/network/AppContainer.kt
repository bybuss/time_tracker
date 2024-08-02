package com.example.time_tracker.data.network

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

/**
 * @author bybuss
*/

interface AppContainer {
    val timeTrackerRepository: TimeTrackerRepository
}

class AppContainerImpl: AppContainer {
    private val baseUrl = "http://31.128.45.95:8000/graphql"

//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(Interceptor { chain ->
//            val request = chain.request().newBuilder()
//                .addHeader("Authorization", token)
//                .build()
//            chain.proceed(request)
//        })
//        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor())
        .build()

    private val apolloClient = ApolloClient.Builder()
        .serverUrl(baseUrl)
        .okHttpClient(okHttpClient)
        .build()

    override val timeTrackerRepository: TimeTrackerRepository by lazy {
        TimeTrackerRepository(apolloClient)
    }
}

private class AuthorizationInterceptor() : Interceptor {
    private val token = "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJ0eXBlIjoiYWNjZXNzIiwic3ViIjoiZDY5NWYyNTYtMjFmMy00YmEwLTljNTItYmVjNzYyOTgxM2EyIiwiZW1haWwiOiJiYWJha2FwYTcyOUBnbWFpbC5jb20iLCJyb2xlX2lkIjoxLCJleHAiOjE3MjI3MTc4MjEsImlhdCI6MTcyMjYyNzgyMX0.pfrZoCGhU-iugUIUSSrEyJfGk7zAEvffQAn0YeaBEAOqv1gk6jHHftVGfHqDpHgGMmHuNLaVmLBJ3AK_TqWjwldUBEUPG7CcSDsab9Ji5q_OisH1VhXA3pFpMQrt4uD2N6P6x1pfPGu8uaTvHzJMa0YpvWl2Ugmmwckizdw1nigemHRoyoRdaWiZtHdxB1GKi13y3dZIU0SoqqI46esDOP-A0pe4zI02oTVY37dM7PVkmWrcf87JWRuid9OkDLVhUNkC5tHLqR3hHDmaWz49QbvZq9KaS8E7dUyAbdl3b4ZdayBNL-wV4CUZP_UxeGOOowDU8dsG5mtKlHMOP8XY3w"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()

        return chain.proceed(request)
    }
}
