package com.example.time_tracker.data.network

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.time_tracker.AddOrganizationMutation
import com.example.time_tracker.AddRoleMutation
import com.example.time_tracker.AddUserMutation
import com.example.time_tracker.AuthUserQuery
import com.example.time_tracker.domain.model.AccessToken
import com.example.time_tracker.domain.model.AuthUserResponse
import com.example.time_tracker.domain.network.TimeTrackerClient
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.serialization.json.Json

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
            firstName, lastName, roleId, email, password
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
        Log.d("TimeTrackerRepository", responseJson)
        val accessToken = Json.decodeFromString<AuthUserResponse>(responseJson).accessToken
        return AccessToken(
            token = accessToken.token,
            expiresIn = accessToken.expiresIn,
            createdAt = accessToken.createdAt
        )
    }
}