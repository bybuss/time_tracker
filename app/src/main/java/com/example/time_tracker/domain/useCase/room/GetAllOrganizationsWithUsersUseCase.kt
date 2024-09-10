package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.organization.OrganizationRepository
import com.example.time_tracker.data.local.room.relations.userOrg.OrganizationWithUsers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllOrganizationsWithUsersUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
) {

    fun getAllOrganizationsWithUsers(): Flow<List<OrganizationWithUsers>> {
        return organizationRepository.getAllOrganizationsWithUsers()
    }
}