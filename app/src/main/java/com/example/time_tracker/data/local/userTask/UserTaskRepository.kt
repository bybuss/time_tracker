package com.example.time_tracker.data.local.userTask

import com.example.time_tracker.base.BaseRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface UserTaskRepository: BaseRepository<UserTask> {
    fun getAllUserTask(): Flow<List<UserTask>>
}