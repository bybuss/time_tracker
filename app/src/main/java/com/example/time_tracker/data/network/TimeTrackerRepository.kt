package com.example.time_tracker.data.network

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.example.time_tracker.AddRoleMutation
import com.example.time_tracker.domain.network.TimeTrackerClient

/**
 * @author bybuss
 */
class TimeTrackerRepository(private val apolloClient: ApolloClient): TimeTrackerClient {
    override suspend fun addRole(name: String, permissions: Map<String, Boolean>): Int {
        val response = apolloClient.mutation(AddRoleMutation(name, permissions)).execute()

        return when {
            response.hasErrors() -> {
                Log.e("Login", "Failed to login: ${response.errors?.firstOrNull()?.message}")
                -1
            }

            else -> { response.data?.addRole?.id ?: -1 }
        }
    }
}