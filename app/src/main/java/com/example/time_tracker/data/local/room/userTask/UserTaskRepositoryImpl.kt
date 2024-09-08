package com.example.time_tracker.data.local.room.userTask

import com.example.time_tracker.data.local.room.base.BaseRepositoryImpl
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
class UserTaskRepositoryImpl(
    private val userTaskDao: UserTaskDao
): BaseRepositoryImpl<UserTask>(userTaskDao), UserTaskRepository {
    override fun getAllUserTask(): Flow<List<UserTask>> = userTaskDao.getAllUserTask()
}