package com.example.time_tracker.data.local.role

import com.example.time_tracker.data.local.BaseRepositoryImpl

/**
 * @author bybuss
 */
class RoleRepositoryImpl(
    private val roleDao: RoleDao
): BaseRepositoryImpl<Role>(roleDao), RoleRepository {
}