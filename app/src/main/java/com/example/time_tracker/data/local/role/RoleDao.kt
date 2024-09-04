package com.example.time_tracker.data.local.role

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.time_tracker.base.BaseDao
import com.example.time_tracker.data.local.relations.RoleWithUsers
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface RoleDao: BaseDao<Role> {
    @Query("SELECT * FROM roles")
    fun getAllRoles(): Flow<List<Role>>

    @Transaction
    @Query("SELECT * FROM roles")
    fun getAllRolesWithUsers(): Flow<List<RoleWithUsers>>
}