package com.example.time_tracker.data.local.room.userOrg

import com.example.time_tracker.data.local.room.base.BaseRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface UserOrgRepository: BaseRepository<UserOrg> {
    fun getAllUserOrg(): Flow<List<UserOrg>>
}