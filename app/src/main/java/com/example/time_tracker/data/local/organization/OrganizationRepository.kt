package com.example.time_tracker.data.local.organization

import com.example.time_tracker.base.BaseRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface OrganizationRepository: BaseRepository<Organization> {
    fun getAllOrganizations(): Flow<List<Organization>>
}