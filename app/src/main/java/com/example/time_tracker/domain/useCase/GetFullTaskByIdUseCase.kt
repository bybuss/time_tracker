package com.example.time_tracker.domain.useCase

import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetFullTaskByIdUseCase @Inject constructor(private val graphQLClient: GraphQLClient) {

    suspend fun getFullTaskById(id: Int): FullTask {
        return graphQLClient.getFullTaskById(id)
    }
}