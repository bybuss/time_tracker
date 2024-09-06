package com.example.time_tracker.data.local.organization

import com.example.time_tracker.base.BaseRepository
import com.example.time_tracker.data.local.relations.userOrg.OrganizationWithUsers
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface OrganizationRepository: BaseRepository<Organization> {
    fun getAllOrganizations(): Flow<List<Organization>>

    fun getAllOrganizationsWithUsers(): Flow<List<OrganizationWithUsers>>
}