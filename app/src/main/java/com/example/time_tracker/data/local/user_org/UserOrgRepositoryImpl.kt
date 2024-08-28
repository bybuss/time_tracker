package com.example.time_tracker.data.local.user_org

import com.example.time_tracker.base.BaseRepositoryImpl

/**
 * @author bybuss
 */
class UserOrgRepositoryImpl(
    private val userOrgDao: UserOrgDao
): BaseRepositoryImpl<UserOrg>(userOrgDao), UserOrgRepository {
}