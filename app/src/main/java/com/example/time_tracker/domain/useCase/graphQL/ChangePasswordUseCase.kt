package com.example.time_tracker.domain.useCase.graphQL

import com.example.time_tracker.domain.network.GraphQLClient
import javax.inject.Inject

/**
 * @author bybuss
 */
class ChangePasswordUseCase @Inject constructor(private val graphQLClient: GraphQLClient) {

    suspend fun changePassword(newPassword: String, changePasswordToken: String): Boolean {
        return graphQLClient.changePassword(newPassword, changePasswordToken)
    }
}