package com.example.time_tracker.data.local.room.organization

import com.example.time_tracker.data.local.room.base.BaseRepository
import com.example.time_tracker.data.local.room.relations.userOrg.OrganizationWithUsers
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface OrganizationRepository: BaseRepository<Organization> {
    fun getAllOrganizations(): Flow<List<Organization>>

    fun getAllOrganizationsWithUsers(): Flow<List<OrganizationWithUsers>>
}