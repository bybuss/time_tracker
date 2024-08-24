package com.example.time_tracker.data.local.organization

import com.example.time_tracker.data.local.base.BaseRepositoryImpl

/**
 * @author bybuss
 */
class OrganizationRepositoryImpl(
    private val organizationDao: OrganizationDao
): BaseRepositoryImpl<Organization>(organizationDao), OrganizationRepository {
}