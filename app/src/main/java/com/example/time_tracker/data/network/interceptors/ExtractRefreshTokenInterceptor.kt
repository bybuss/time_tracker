package com.example.time_tracker.data.network.interceptors

import com.example.time_tracker.data.network.TokenStoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author bybuss
 */
class ExtractRefreshTokenInterceptor (
    private val tokenStoreRepository: TokenStoreRepository,
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
                    tokenStoreRepository.saveRefreshToken(it)
                }
            }

            return response
        }

        return chain.proceed(request)
    }
}