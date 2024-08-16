package com.example.time_tracker.data.network.interceptors

import com.example.time_tracker.data.network.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author bybuss
 */
class AddRefreshTokenInterceptor (
    private val tokenManager: TokenManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.header("X-Refresh-Token-Request") == "true") {
            val refreshToken = tokenManager.getRefreshToken()

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
