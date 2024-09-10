package com.example.time_tracker.domain.useCase.graphQL

import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class AddOrganizationUseCase @Inject constructor(private val graphQLClient: GraphQLClient) {

    suspend fun addOrganization(name: String, description: String): Int {
        return graphQLClient.addOrganization(name, description)
    }
}