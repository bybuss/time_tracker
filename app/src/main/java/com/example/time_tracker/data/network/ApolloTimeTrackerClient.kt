package com.example.time_tracker.data.network

import com.example.time_tracker.domain.model.User
import com.example.time_tracker.domain.network.TimeTrackerClient

/**
 * @author bybuss
 */
class ApolloTimeTrackerClient : TimeTrackerClient {
    override suspend fun getUserByEmail(email: String): User? {
        TODO("Not yet implemented")
    }
}