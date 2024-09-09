package com.example.time_tracker.domain.useCase

import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllFullTasksByAssignerIdUseCase @Inject constructor(private val graphQLClient: GraphQLClient) {

    suspend fun getAllFullTasksByAssignerId(assignerId: String): List<FullTask> {
        return graphQLClient.getAllFullTasksByAssignerId(assignerId).sortedBy { it.id }
    }
}