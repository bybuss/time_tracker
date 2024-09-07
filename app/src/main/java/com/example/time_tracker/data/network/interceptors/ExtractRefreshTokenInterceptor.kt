package com.example.time_tracker.data.network.interceptors

import com.example.time_tracker.data.local.dataStore.TokenDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author bybuss
 */
class ExtractRefreshTokenInterceptor (
    private val tokenDataSource: TokenDataSource,
    private val coroutineScope: CoroutineScope
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.header("Access-Token-Request") == "true") {
            val response = chain.proceed(chain.request())

            val setCookieHeaders = response.headers("Set-Cookie")
            var refreshToken = ""

            setCookieHeaders.forEach { header ->
                if (header.startsWith("refreshToken")) { refreshToken = header }
            }

            refreshToken.let {
                coroutineScope.launch {
                    tokenDataSource.saveRefreshToken(it)
                }
            }

            return response
        }

        return chain.proceed(request)
    }
}