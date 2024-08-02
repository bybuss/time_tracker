package com.example.time_tracker.domain.network

/**
 * @author bybuss
 */
interface TimeTrackerClient {
    suspend fun addRole(name: String, permissions: Map<String, Boolean>): Int
}