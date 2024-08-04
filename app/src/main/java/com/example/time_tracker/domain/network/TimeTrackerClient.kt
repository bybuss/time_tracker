package com.example.time_tracker.domain.network

import com.example.time_tracker.domain.model.AccessToken

/**
 * @author bybuss
 */
interface TimeTrackerClient {

    suspend fun addRole(name: String, permissions: Map<String, Boolean>): Int

    suspend fun addOrganization(name: String, description: String): Int

    suspend fun addUser(
        firstName: String,
        lastName: String,
        roleId: Int,
        email: String,
        password: String
    ): String

    suspend fun authUser(email: String, password: String): AccessToken
}