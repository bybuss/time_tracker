package com.example.time_tracker.data.local.room.userOrg

import com.example.time_tracker.base.BaseRepositoryImpl
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
class UserOrgRepositoryImpl(
    private val userOrgDao: UserOrgDao
): BaseRepositoryImpl<UserOrg>(userOrgDao), UserOrgRepository {
    override fun getAllUserOrg(): Flow<List<UserOrg>> = userOrgDao.getAllUserOrg()
}