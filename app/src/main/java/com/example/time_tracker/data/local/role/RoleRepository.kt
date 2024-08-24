package com.example.time_tracker.data.local.role

import com.example.time_tracker.data.local.base.BaseRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface RoleRepository: BaseRepository<Role> {
    fun getAllRoles(): Flow<List<Role>>
}