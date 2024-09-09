package com.example.time_tracker.domain.useCase

import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class AddUserUseCase @Inject constructor(private val graphQLClient: GraphQLClient)  {

    suspend fun addUser(
        firstName: String,
        lastName: String,
        roleId: Int,
        email: String,
        password: String
    ): String {
        return graphQLClient.addUser(firstName, lastName, roleId, email, password)
    }
}