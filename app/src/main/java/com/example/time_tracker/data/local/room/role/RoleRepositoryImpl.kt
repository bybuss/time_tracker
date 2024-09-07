package com.example.time_tracker.data.local.room.role

import com.example.time_tracker.base.BaseRepositoryImpl
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
class RoleRepositoryImpl(
    private val roleDao: RoleDao
): BaseRepositoryImpl<Role>(roleDao), RoleRepository {
    override fun getAllRoles(): Flow<List<Role>> = roleDao.getAllRoles()
}