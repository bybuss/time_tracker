package com.example.time_tracker.domain.network

import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface TokenStore {
    suspend fun saveAccessToken(accessToken: String)
    suspend fun saveAccessTokenExpiresTime(expiresTime: String)
    suspend fun saveAccessTokenCreatedTime(createdTime: String)
    fun getAccessToken(): Flow<String>
    fun getAccessTokenExpiresTime(): Flow<String>
    fun getAccessTokenCreatedTime(): Flow<String>

    suspend fun saveRefreshToken(refreshToken: String)
    fun getRefreshToken(): Flow<String>

    suspend fun clearTokens()
}