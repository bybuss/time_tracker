package com.example.time_tracker.domain.useCase.graphQL

import com.example.time_tracker.domain.model.SimpleTask
import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllSimpleTasksByAssignerIdUseCase @Inject constructor(private val graphQLClient: GraphQLClient) {

    suspend fun getAllSimpleTasksByAssignerId(assignerId: String): List<SimpleTask> {
        return graphQLClient.getAllSimpleTasksByAssignerId(assignerId).sortedBy { it.id }
    }
}