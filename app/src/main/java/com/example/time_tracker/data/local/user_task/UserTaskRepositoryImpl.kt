package com.example.time_tracker.data.local.user_task

import com.example.time_tracker.base.BaseRepositoryImpl

/**
 * @author bybuss
 */
class UserTaskRepositoryImpl(
    private val userTaskDao: UserTaskDao
): BaseRepositoryImpl<UserTask>(userTaskDao), UserTaskRepository {
}