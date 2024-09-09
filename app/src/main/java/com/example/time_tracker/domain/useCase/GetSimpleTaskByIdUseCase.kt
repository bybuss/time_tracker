package com.example.time_tracker.domain.useCase

import com.example.time_tracker.domain.model.SimpleTask
import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetSimpleTaskByIdUseCase @Inject constructor(private val graphQLClient: GraphQLClient) {

    suspend fun  getSimpleTaskById(id: Int): SimpleTask {
        return graphQLClient.getSimpleTaskById(id)
    }
}