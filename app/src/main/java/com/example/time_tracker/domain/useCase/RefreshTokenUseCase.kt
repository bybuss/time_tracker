package com.example.time_tracker.domain.useCase

import com.example.time_tracker.domain.model.AccessToken
import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class RefreshTokenUseCase @Inject constructor(private val graphQLClient: GraphQLClient) {

    suspend fun refreshToken(): AccessToken {
        return graphQLClient.refreshToken()
    }
}