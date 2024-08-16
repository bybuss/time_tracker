package com.example.time_tracker.data.network.interceptors

import com.example.time_tracker.data.network.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author bybuss
 */
class ExtractRefreshTokenInterceptor (
    private val tokenManager: TokenManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.header("X-Refresh-Token-Request") == "true") {
            val response = chain.proceed(chain.request())

            val setCookieHeaders = response.headers("Set-Cookie")
            var refreshToken: String = ""

            setCookieHeaders.forEach { header ->
                if (header.startsWith("refreshToken")) { refreshToken = header }
            }

            refreshToken.let { tokenManager.updateRefreshToken(it) }

            return response
        }

        return chain.proceed(request)
    }
}