package com.example.time_tracker.domain.network

import com.example.time_tracker.domain.model.AccessToken
import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.model.SimpleTask

/**
 * @author bybuss
 */
interface GraphQLClient {

    suspend fun addRole(
        name: String,
        permissions: Map<String, Map<String, Boolean>>
    ): Int

    suspend fun addOrganization(name: String, description: String): Int

    suspend fun addUser(
        firstName: String,
        lastName: String,
        roleId: Int,
        email: String,
        password: String
    ): String

    suspend fun authUser(email: String, password: String): AccessToken

    suspend fun refreshToken(): AccessToken

    suspend fun addProject(
        name: String,
        organizationId: Int,
        description: String
    ): Int

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
    ): Int

    suspend fun getAllFullTasksByAssignerId(
        assignerId: String
    ): List<FullTask>

    suspend fun getFullTaskById(id: Int): FullTask

    suspend fun getAllSimpleTasksByAssignerId(
        assignerId: String
    ): List<SimpleTask>

    suspend fun getSimpleTaskById(id: Int): SimpleTask

    suspend fun requestChangePassword(
        id: String,
        firstName: String,
        lastName: String,
        email: String
    ): Boolean

    suspend fun changePassword(
        newPassword: String,
        changePasswordToken: String
    ): Boolean
}