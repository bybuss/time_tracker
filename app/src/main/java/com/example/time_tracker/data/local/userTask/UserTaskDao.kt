package com.example.time_tracker.data.local.userTask

import androidx.room.Dao
import androidx.room.Query
import com.example.time_tracker.base.BaseDao
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface UserTaskDao: BaseDao<UserTask> {
    @Query("SELECT * FROM user_task")
    fun getAllUserTask(): Flow<List<UserTask>>
}