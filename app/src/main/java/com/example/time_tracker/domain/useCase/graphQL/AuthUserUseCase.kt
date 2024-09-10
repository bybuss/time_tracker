package com.example.time_tracker.domain.useCase.graphQL

import com.example.time_tracker.domain.model.AccessToken
import com.example.time_tracker.domain.network.GraphQLClient

/**
 * @author bybuss
 */
class AuthUserUseCase(private val graphQLClient: GraphQLClient) {

    suspend fun authUser(email: String, password: String): AccessToken {
        return graphQLClient.authUser(email, password)
    }
}