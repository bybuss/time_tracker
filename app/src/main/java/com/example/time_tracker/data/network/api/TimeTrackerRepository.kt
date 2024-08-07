package com.example.time_tracker.data.network.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.example.time_tracker.AddOrganizationMutation
import com.example.time_tracker.AddProjectMutation
import com.example.time_tracker.AddRoleMutation
import com.example.time_tracker.AddTaskMutation
import com.example.time_tracker.AddUserMutation
import com.example.time_tracker.AuthUserQuery
import com.example.time_tracker.GetFullTaskByAssignerIdQuery
import com.example.time_tracker.GetSimpleTaskByAssignerIdQuery
import com.example.time_tracker.RefreshTokenQuery
import com.example.time_tracker.data.network.convertToJson
import com.example.time_tracker.data.network.toSimpleTask
import com.example.time_tracker.data.network.toFulTask
import com.example.time_tracker.domain.model.AccessToken
import com.example.time_tracker.domain.model.AuthUserResponse
import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.model.SimpleTask
import com.example.time_tracker.domain.network.TimeTrackerClient
import kotlinx.serialization.json.Json
import java.util.UUID

/**
 * @author bybuss
 */
class TimeTrackerRepository(private val apolloClient: ApolloClient): TimeTrackerClient {

    override suspend fun addRole(name: String, permissions: Map<String, Boolean>): Int {
        val response = apolloClient.mutation(AddRoleMutation(name, permissions)).execute()

        if (response.hasErrors()) {
            throw ApolloException(response.errors?.firstOrNull()?.message)
        }

        return response.data?.addRole?.id
            ?: throw ApolloException("Failed to add role: No ID returned")
    }

    override suspend fun addOrganization(name: String, description: String): Int {
        val response = apolloClient.mutation(AddOrganizationMutation(name, description)).execute()

        if (response.hasErrors()) {
            throw ApolloException(response.errors?.firstOrNull()?.message)
        }

        return response.data?.addOrganization?.id
            ?: throw ApolloException("Failed to add organization: No ID returned")
    }

    override suspend fun addUser(
        firstName: String,
        lastName: String,
        roleId: Int,
        email: String,
        password: String
    ): String {
        val response = apolloClient.mutation(AddUserMutation(
            firstName,
            lastName,
            roleId,
            email,
            password
        )).execute()

        if (response.hasErrors()) {
            throw ApolloException(response.errors?.firstOrNull()?.message)
        }

        return (response.data?.addUser?.id
            ?: throw ApolloException("Failed to add user: No ID returned")
        ).toString()
    }

    override suspend fun authUser(email: String, password: String): AccessToken {
        val response = apolloClient.query(AuthUserQuery(email, password)).execute()

        if (response.hasErrors()) {
            throw ApolloException(response.errors?.firstOrNull()?.message)
        }

        val responseJson = convertToJson((
                response.data?.authUser ?: throw ApolloException("User not found")
        ).toString())

        val accessToken = Json.decodeFromString<AuthUserResponse>(responseJson).accessToken

        return AccessToken(
            token = accessToken.token,
            expiresIn = accessToken.expiresIn,
            createdAt = accessToken.createdAt
        )
    }

    override suspend fun refreshToken(): AccessToken {
        val response = apolloClient.query(RefreshTokenQuery()).execute()

        if (response.hasErrors()) {
            throw ApolloException(response.errors?.firstOrNull()?.message)
        }

        val responseJson = convertToJson((
                response.data?.refresh ?: throw ApolloException("User not found")
        ).toString())

        val accessToken = Json.decodeFromString<AuthUserResponse>(responseJson).accessToken

        return AccessToken(
            token = accessToken.token,
            expiresIn = accessToken.expiresIn,
            createdAt = accessToken.createdAt
        )
    }

    override suspend fun addProject(name: String, organizationId: Int, description: String): Int {
        val response = apolloClient.mutation(
            AddProjectMutation(name, organizationId, description)
        ).execute()

        if (response.hasErrors()) {
            throw ApolloException(response.errors?.firstOrNull()?.message)
        }

        return response.data?.addProject?.id
            ?: throw ApolloException("Failed to add project: No ID returned")
    }

    override suspend fun addTask(
        name: String,
        description: String,
        isDone: Boolean,
        assignerId: UUID,
        color: String,
        duration: Int,
        endDate: String?,
        difficulty: String,
        projectId: Int,
        groupId: Int?,
        assignees: List<Map<String, Any>>
    ): Int {
        val response  = apolloClient.mutation(
            AddTaskMutation(
                name,
                description,
                isDone,
                assignerId,
                color,
                duration,
                Optional.presentIfNotNull(endDate),
                difficulty,
                projectId,
                Optional.presentIfNotNull(groupId),
                assignees
            )
        ).execute()

        if (response.hasErrors()) {
            throw ApolloException(response.errors?.firstOrNull()?.message)
        }

        return response.data?.addTask?.id
            ?: throw ApolloException("Failed to add task: No ID returned")
    }

    override suspend fun getFullTaskById(assignerId: UUID): FullTask? {
        val response = apolloClient.query(GetFullTaskByAssignerIdQuery(assignerId)).execute()

        if (response.hasErrors()) {
            throw ApolloException(response.errors?.firstOrNull()?.message)
        }

        return response.data?.getTask?.firstOrNull()?.toFulTask()
    }

    override suspend fun getSimpleTaskByAssignerId(assignerId: UUID): SimpleTask? {
        val response = apolloClient.query(GetSimpleTaskByAssignerIdQuery(assignerId)).execute()

        if (response.hasErrors()) {
            throw ApolloException(response.errors?.firstOrNull()?.message)
        }

        return response.data?.getTask?.firstOrNull()?.toSimpleTask()
    }
}