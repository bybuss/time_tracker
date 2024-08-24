package com.example.time_tracker.data.local.organization

import com.example.time_tracker.data.local.base.BaseRepositoryImpl
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
class OrganizationRepositoryImpl(
    private val organizationDao: OrganizationDao
): BaseRepositoryImpl<Organization>(organizationDao), OrganizationRepository {
    override fun getAllOrganizations(): Flow<List<Organization>>
        = organizationDao.getAllOrganizations()
}