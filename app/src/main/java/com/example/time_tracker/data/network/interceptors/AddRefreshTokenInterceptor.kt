package com.example.time_tracker.data.network.interceptors

import android.util.Log
import com.example.time_tracker.data.network.TokenStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author bybuss
 */
class AddRefreshTokenInterceptor (
    private val tokenStoreRepository: TokenStoreRepository
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.header("Refresh-Token-Request") == "true") {
            val refreshToken = runBlocking {
                tokenStoreRepository.getRefreshToken().first()
            }

            if (refreshToken.isNotEmpty()) {
                val newRequest = request.newBuilder()
                    .addHeader("Cookie", refreshToken)
                    .build()

                return chain.proceed(newRequest)
            }
        }

        return chain.proceed(request)
    }
}
