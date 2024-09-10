package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.relations.userOrg.UserWithOrganizations
import com.example.time_tracker.data.local.room.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllUsersWithOrganizationsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    fun getAllUsersWithOrganizations(): Flow<List<UserWithOrganizations>> {
        return userRepository.getAllUsersWithOrganizations()
    }
}