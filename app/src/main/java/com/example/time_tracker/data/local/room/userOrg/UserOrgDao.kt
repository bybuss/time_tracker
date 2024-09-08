package com.example.time_tracker.data.local.room.userOrg

import androidx.room.Dao
import androidx.room.Query
import com.example.time_tracker.data.local.room.base.BaseDao
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface UserOrgDao: BaseDao<UserOrg> {
    @Query("SELECT * FROM user_org")
    fun getAllUserOrg(): Flow<List<UserOrg>>
}