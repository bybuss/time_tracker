package com.example.time_tracker.data.network

import android.util.Log

/**
 * @author bybuss
 */

// TODO: УБРАТЬ ПОТОМ TokenManager
class TokenManager {
    @Volatile private var accessToken: String = ""
    @Volatile private var refreshToken: String = ""

    @Synchronized
    fun updateAccessToken(newToken: String) {
        accessToken = newToken
        Log.d("TokenManager", "accessToken updated: $accessToken")
    }

    @Synchronized
    fun getAccessToken(): String {
        Log.d("TokenManager", "Current accessToken: $accessToken")
        return accessToken
    }

    @Synchronized
    fun updateRefreshToken(newToken: String) {
        refreshToken = newToken
        Log.d("TokenManager", "refreshToken updated: $refreshToken")
    }

    @Synchronized
    fun getRefreshToken(): String {
        Log.d("TokenManager", "Current refreshToken: $refreshToken")
        return refreshToken
    }
}