package com.example.time_tracker.domain.useCase.graphQL

import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class RequestChangePasswordUseCase @Inject constructor(private val graphQLClient: GraphQLClient) {

    suspend fun requestChangePassword(
        id: String,
        firstName: String,
        lastName: String,
        email: String
    ): Boolean {
        return graphQLClient.requestChangePassword(id, firstName, lastName, email)
    }
}