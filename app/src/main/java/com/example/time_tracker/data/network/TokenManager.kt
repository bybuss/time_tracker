package com.example.time_tracker.data.network

import android.util.Log

/**
 * @author bybuss
 */

// TODO: ЗАМЕНИТЬ ПОТОМ TokenManager НА БД
class TokenManager {
    @Volatile private var accessToken: String = ""
    @Volatile private var refreshToken: String = ""

    fun updateAccessToken(newToken: String) {
        accessToken = newToken
        Log.d("TokenManager", "accessToken updated: $accessToken")
    }

    fun getAccessToken(): String {
        Log.d("TokenManager", "Current accessToken: $accessToken")
        return accessToken
    }

    fun updateRefreshToken(newToken: String) {
        refreshToken = newToken
        Log.d("TokenManager", "refreshToken updated: $refreshToken")
    }

    fun getRefreshToken(): String {
        Log.d("TokenManager", "Current refreshToken: $refreshToken")
        return refreshToken
    }
}