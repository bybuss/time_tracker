package com.example.time_tracker.domain.network

import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface TokenStore {
    suspend fun saveAccessToken(accessToken: String)
    fun getAccessToken(): Flow<String>

    suspend fun saveRefreshToken(refreshToken: String)
    fun getRefreshToken(): Flow<String>
}