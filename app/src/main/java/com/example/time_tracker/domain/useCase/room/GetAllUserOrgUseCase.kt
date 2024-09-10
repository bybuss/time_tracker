package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.userOrg.UserOrg
import com.example.time_tracker.data.local.room.userOrg.UserOrgRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllUserOrgUseCase @Inject constructor(
    private val userOrgRepository: UserOrgRepository
) {

    fun getAllUserOrg(): Flow<List<UserOrg>> {
        return userOrgRepository.getAllUserOrg()
    }
}