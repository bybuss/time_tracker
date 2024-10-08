package com.example.time_tracker.data.local.room.organization

import com.example.time_tracker.data.local.room.base.BaseRepositoryImpl
import com.example.time_tracker.data.local.room.relations.userOrg.OrganizationWithUsers
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
class OrganizationRepositoryImpl(
    private val organizationDao: OrganizationDao
): BaseRepositoryImpl<Organization>(organizationDao), OrganizationRepository {
    override fun getAllOrganizations(): Flow<List<Organization>>
        = organizationDao.getAllOrganizations()

    override fun getAllOrganizationsWithUsers(): Flow<List<OrganizationWithUsers>>
        = organizationDao.getAllOrganizationsWithUsers()
}