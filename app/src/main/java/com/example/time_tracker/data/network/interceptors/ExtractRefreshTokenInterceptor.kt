package com.example.time_tracker.data.network.interceptors

import android.util.Log
import com.example.time_tracker.data.network.TokenStoreRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author bybuss
 */
class ExtractRefreshTokenInterceptor (
    private val tokenStoreRepository: TokenStoreRepository
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.header("Access-Token-Request") == "true") {
            val response = chain.proceed(chain.request())

            val setCookieHeaders = response.headers("Set-Cookie")
            var refreshToken = ""

            setCookieHeaders.forEach { header ->
                if (header.startsWith("refreshToken")) { refreshToken = header }
                Log.d("Interceptor", "Come refresh token: $header")
            }

            refreshToken.let {
                GlobalScope.launch {
                    tokenStoreRepository.saveRefreshToken(it)
                    Log.d("Interceptor", "Saving refresh token: $it")
                }
            }

            return response
        }

        return chain.proceed(request)
    }
}