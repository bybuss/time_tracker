package com.example.time_tracker.domain.network

import com.example.time_tracker.domain.model.User

/**
 * @author bybuss
 */
interface TimeTrackerClient {
    suspend fun getUserByEmail(email: String): User?
}