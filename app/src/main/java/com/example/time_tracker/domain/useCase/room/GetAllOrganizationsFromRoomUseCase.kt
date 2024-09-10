package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.organization.Organization
import com.example.time_tracker.data.local.room.organization.OrganizationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllOrganizationsFromRoomUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
) {

    fun getAllOrganizations(): Flow<List<Organization>> {
        return organizationRepository.getAllOrganizations()
    }
}