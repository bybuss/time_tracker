package com.example.time_tracker.domain.useCase.graphQL

import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class AddTaskUseCase @Inject constructor(private val graphQLClient: GraphQLClient) {

    suspend fun addTask(
        name: String,
        description: String,
        isDone: Boolean,
        assignerId: String,
        color: String,
        duration: Int,
        endDate: String?,
        difficulty: String,
        projectId: Int,
        groupId: Int?,
        assignees: List<Map<String, Any>>
    ): Int {
        return graphQLClient.addTask(
            name = name,
            description = description,
            isDone = isDone,
            assignerId = assignerId,
            color = color,
            duration = duration,
            endDate = endDate,
            difficulty = difficulty,
            projectId = projectId,
            groupId = groupId,
            assignees = assignees
        )
    }
}