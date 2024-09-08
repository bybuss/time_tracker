package com.example.time_tracker.domain.useCase

import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class AddRoleUseCase @Inject constructor(private val graphQLClient: GraphQLClient) {

    suspend fun addRole(name: String, permissions: Map<String, Map<String, Boolean>>): Int {
        return graphQLClient.addRole(name, permissions)
    }
}