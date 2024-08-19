package com.example.time_tracker.data.network

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.time_tracker.domain.network.TokenStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * @author bybuss
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token_store")

class TokenStoreRepository(private val context: Context): TokenStore {
    private object PreferencesKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val ACCESS_TOKEN_EXPIRES_TIME = stringPreferencesKey("access_token_expires_time")
        val ACCESS_TOKEN_CREATED_TIME = stringPreferencesKey("access_token_created_time")
    }

    override suspend fun saveAccessToken(accessToken: String) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun saveAccessTokenExpiresTime(expiresTime: String) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.ACCESS_TOKEN_EXPIRES_TIME] = expiresTime
        }
    }

    override suspend fun saveAccessTokenCreatedTime(createdTime: String) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.ACCESS_TOKEN_CREATED_TIME] = createdTime
        }
    }

    override fun getAccessToken(): Flow<String> {
        return context.dataStore.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else { throw exception }
            }.map { it[PreferencesKeys.ACCESS_TOKEN] ?: "" }
    }

    override fun getAccessTokenExpiresTime(): String {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else { throw exception }
        }.map { it[PreferencesKeys.ACCESS_TOKEN_EXPIRES_TIME] ?: "" }.toString()
    }

    override fun getAccessTokenCreatedTime(): String {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else { throw exception }
        }.map { it[PreferencesKeys.ACCESS_TOKEN_CREATED_TIME] ?: "" }.toString()
    }

    override suspend fun saveRefreshToken(refreshToken: String) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.REFRESH_TOKEN] = refreshToken
        }
    }

    override fun getRefreshToken(): Flow<String> {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else { throw exception }
        }.map { it[PreferencesKeys.REFRESH_TOKEN] ?: "" }
    }

    override suspend fun clearTokens() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}