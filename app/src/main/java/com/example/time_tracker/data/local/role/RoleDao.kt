package com.example.time_tracker.data.local.role

import androidx.room.Dao
import androidx.room.Query
import com.example.time_tracker.data.local.base.BaseDao
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface RoleDao: BaseDao<Role> {
    @Query("SELECT * FROM roles")
    fun getAllRoles(): Flow<List<Role>>
}