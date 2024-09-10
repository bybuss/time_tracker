package com.example.time_tracker.domain.useCase.graphQL

import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class AddProjectUseCase @Inject constructor(private val graphQLClient: GraphQLClient)  {

    suspend fun addProject(name: String, organizationId: Int, description: String): Int {
        return graphQLClient.addProject(name, organizationId, description)
    }
}