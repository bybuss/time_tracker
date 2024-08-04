package com.example.time_tracker.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author bybuss
 */

@Serializable
data class AuthUserResponse(
    val accessToken: AccessToken
)

@Serializable
data class AccessToken(
    val token: String,
    @SerialName("expires_in") val expiresIn: String,
    @SerialName("created_at") val createdAt: String
)
