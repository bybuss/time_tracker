package com.example.time_tracker.data.local.room.group

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.time_tracker.data.local.room.base.BaseDao
import com.example.time_tracker.data.local.room.relations.GroupWithTasks
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface GroupDao: BaseDao<Group> {
    @Transaction
    @Query("SELECT * FROM groups")
    fun getAllGroupsWithTasks(): Flow<List<GroupWithTasks>>
}