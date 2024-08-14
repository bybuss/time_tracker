package com.example.time_tracker.data.network.interceptors

import com.example.time_tracker.data.network.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author bybuss
 */
class RefreshTokenInterceptor (
    private val tokenManager: TokenManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.header("X-Refresh-Token-Request") == "true") {
            val buffer = okio.Buffer()
            request.body?.writeTo(buffer)
            val requestBodyStr = buffer.readUtf8()

            if (requestBodyStr.contains("RefreshTokenQuery")) {
                val refreshToken = tokenManager.getRefreshToken()
                val newRequest = request.newBuilder()
                    .addHeader("Cookie", refreshToken)
                    .build()
                return chain.proceed(newRequest)
            }
        }

        return chain.proceed(request)
    }
}
